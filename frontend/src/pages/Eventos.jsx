import { useState, useEffect } from 'react';
import { api } from '../services/api';

function Eventos() {
    const [eventos, setEventos] = useState([]);
    const [usuarios, setUsuarios] = useState([]);
    const [usuarioAtualId, setUsuarioAtualId] = useState(1);
    const [meusEventosNomes, setMeusEventosNomes] = useState([]);
    const [filtroStatus, setFiltroStatus] = useState('todos');
    const [carregando, setCarregando] = useState(true);

    const obterId = (obj, indexFallback = 0) =>
        obj?.id ?? obj?.idPessoa ?? obj?.usuarioId ?? obj?.eventoId ?? (indexFallback + 1);

    useEffect(() => {
        const iniciar = async () => {
            try {
                const [resEventos, resUsuarios] = await Promise.all([
                    api.get('/eventos'),
                    api.get('/usuarios').catch(() => ({ data: [] }))
                ]);
                setEventos(resEventos.data);
                setUsuarios(resUsuarios.data);

                if (resUsuarios.data.length > 0) {
                    setUsuarioAtualId(obterId(resUsuarios.data[0], 0));
                }
            } catch (error) {
                console.error('Erro ao carregar dados iniciais:', error);
            } finally {
                setCarregando(false);
            }
        };
        iniciar();
    }, []);

    useEffect(() => {
        if (!usuarioAtualId) return;
        api.get(`/usuarios/${usuarioAtualId}/eventos`)
            .then((res) => {
                // Guarda o nome dos eventos em que o utilizador já está inscrito
                setMeusEventosNomes(res.data.map((ev) => ev.nome));
            })
            .catch((err) => {
                console.error('Erro ao procurar presenças do utilizador:', err);
                setMeusEventosNomes([]);
            });
    }, [usuarioAtualId]);

    const alternarPresenca = async (evento, evId) => {
        const jaConfirmado = meusEventosNomes.includes(evento.nome);
        try {
            if (jaConfirmado) {
                await api.delete(`/usuarios/${usuarioAtualId}/eventos/${evId}`);
                setMeusEventosNomes(meusEventosNomes.filter((nome) => nome !== evento.nome));
            } else {
                await api.post(`/usuarios/${usuarioAtualId}/eventos/${evId}`);
                setMeusEventosNomes([...meusEventosNomes, evento.nome]);
            }
        } catch (error) {
            // Se retornar 409 (Conflict), o utilizador já estava confirmado na base de dados!
            if (error.response?.status === 409) {
                if (!meusEventosNomes.includes(evento.nome)) {
                    setMeusEventosNomes([...meusEventosNomes, evento.nome]);
                }
                return;
            }
            console.error('Erro detalhado ao atualizar presença:', error.response || error);
            alert(`Não foi possível atualizar a presença (Status: ${error.response?.status || 'Erro'}).`);
        }
    };

    const excluirEvento = async (id, nomeEvento) => {
        if (!window.confirm('Tem a certeza de que deseja excluir este evento?')) return;
        try {
            await api.delete(`/eventos/${id}`);
            setEventos(eventos.filter((evento) => evento.nome !== nomeEvento));
        } catch (error) {
            console.error('Erro ao excluir evento:', error);
            alert('Não foi possível excluir o evento.');
        }
    };

    const eventosFiltrados = filtroStatus === 'todos'
        ? eventos
        : eventos.filter((e) => (e.statusEvento || 'ativo') === filtroStatus);

    if (carregando) {
        return <p>A carregar eventos...</p>;
    }

    return (
        <div>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '15px', flexWrap: 'wrap', gap: '10px' }}>
                <h2>Eventos Culturais</h2>

                <div style={{ display: 'flex', gap: '15px', alignItems: 'center' }}>
                    {usuarios.length > 0 && (
                        <div>
                            <label style={{ marginRight: '8px', fontWeight: 'bold' }}>Simular Utilizador:</label>
                            <select
                                value={usuarioAtualId}
                                onChange={(e) => setUsuarioAtualId(Number(e.target.value))}
                                style={{ padding: '6px 10px', borderRadius: '6px', border: '1px solid #ccc' }}
                            >
                                {usuarios.map((u, idx) => {
                                    const uid = obterId(u, idx);
                                    return (
                                        <option key={uid} value={uid}>
                                            {u.nome} (ID: {uid})
                                        </option>
                                    );
                                })}
                            </select>
                        </div>
                    )}

                    <div>
                        <label style={{ marginRight: '8px', fontWeight: 'bold' }}>Filtrar por estado:</label>
                        <select
                            value={filtroStatus}
                            onChange={(e) => setFiltroStatus(e.target.value)}
                            style={{ padding: '6px 10px', borderRadius: '6px', border: '1px solid #ccc' }}
                        >
                            <option value="todos">Todos</option>
                            <option value="ativo">Ativo</option>
                            <option value="cancelado">Cancelado</option>
                            <option value="encerrado">Encerrado</option>
                        </select>
                    </div>
                </div>
            </div>

            <div style={{ display: 'flex', flexDirection: 'column', gap: '15px' }}>
                {eventosFiltrados.map((evento, idx) => {
                    const evId = obterId(evento, idx);
                    const confirmado = meusEventosNomes.includes(evento.nome);
                    return (
                        <div
                            key={evento.nome || evId}
                            style={{
                                backgroundColor: 'white',
                                padding: '18px',
                                borderRadius: '8px',
                                display: 'flex',
                                justifyContent: 'space-between',
                                alignItems: 'center',
                                borderLeft: confirmado ? '6px solid #8F9C3B' : '6px solid transparent',
                                boxShadow: '0 2px 4px rgba(0,0,0,0.06)'
                            }}
                        >
                            <div>
                                <h3 style={{ margin: '0 0 6px 0', color: '#1F3318' }}>{evento.nome}</h3>
                                <p style={{ margin: '0 0 6px 0' }}>{evento.descricao}</p>
                                <small style={{ color: '#555' }}>
                                    Data: {evento.dataEvento || 'Não informada'} • Estado: <strong>{evento.statusEvento || 'ativo'}</strong>
                                </small>
                            </div>

                            <div style={{ display: 'flex', gap: '10px' }}>
                                <button
                                    onClick={() => alternarPresenca(evento, evId)}
                                    style={{
                                        backgroundColor: confirmado ? '#6c757d' : '#8F9C3B',
                                        color: 'white',
                                        border: 'none',
                                        padding: '8px 12px',
                                        borderRadius: '6px',
                                        cursor: 'pointer',
                                        fontWeight: 'bold'
                                    }}
                                >
                                    {confirmado ? 'Presença Confirmada ✓' : 'Eu Vou'}
                                </button>

                                <button
                                    onClick={() => excluirEvento(evId, evento.nome)}
                                    style={{
                                        backgroundColor: '#d9534f',
                                        color: 'white',
                                        border: 'none',
                                        padding: '8px 12px',
                                        borderRadius: '6px',
                                        cursor: 'pointer',
                                        fontWeight: 'bold'
                                    }}
                                >
                                    Excluir
                                </button>
                            </div>
                        </div>
                    );
                })}
            </div>
        </div>
    );
}

export default Eventos;