import { useState, useEffect } from 'react';
import { api } from '../services/api';

function NovoRegisto() {
    const [nome, setNome] = useState('');
    const [dataEvento, setDataEvento] = useState('');
    const [statusEvento, setStatusEvento] = useState('ativo');
    const [descricao, setDescricao] = useState('');
    const [localId, setLocalId] = useState('');
    const [organizadorId, setOrganizadorId] = useState('');
    const [mensagem, setMensagem] = useState('');

    const [locais, setLocais] = useState([]);
    const [organizadores, setOrganizadores] = useState([]);

    useEffect(() => {
        api.get('/locais')
            .then((res) => setLocais(res.data))
            .catch((err) => console.error('Erro ao buscar locais:', err));

        api.get('/organizadores')
            .then((res) => setOrganizadores(res.data))
            .catch((err) => console.error('Erro ao buscar organizadores:', err));
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMensagem('A registar...');

        try {
            const novoEvento = {
                nome,
                dataEvento,
                statusEvento,
                descricao,
                localId: parseInt(localId),
                organizadorId: parseInt(organizadorId)
            };

            await api.post('/eventos', novoEvento);
            setMensagem('Registo criado com sucesso!');
            setNome('');
            setDataEvento('');
            setStatusEvento('ativo');
            setDescricao('');
            setLocalId('');
            setOrganizadorId('');
        } catch (error) {
            console.error('Erro ao criar registo:', error);
            setMensagem('Erro ao criar registo. Verifique os dados preenchidos.');
        }
    };

    return (
        <div style={{ maxWidth: '600px', margin: '0 auto', padding: '20px', backgroundColor: '#fff', borderRadius: '12px', boxShadow: '0 4px 6px rgba(0,0,0,0.1)' }}>
            <h2>Registar Novo Evento Cultural</h2>
            <p>Preencha os campos obrigatórios exigidos pelo sistema.</p>

            {mensagem && (
                <div style={{ padding: '10px', marginBottom: '15px', backgroundColor: mensagem.includes('sucesso') ? '#d4edda' : '#f8d7da', color: mensagem.includes('sucesso') ? '#155724' : '#721c24', borderRadius: '6px' }}>
                    {mensagem}
                </div>
            )}

            <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '15px' }}>
                <div>
                    <label style={{ display: 'block', marginBottom: '5px', fontWeight: 'bold' }}>Nome do Evento:</label>
                    <input
                        type="text"
                        value={nome}
                        onChange={(e) => setNome(e.target.value)}
                        required
                        style={{ width: '100%', padding: '10px', borderRadius: '6px', border: '1px solid #ccc' }}
                    />
                </div>

                <div style={{ display: 'flex', gap: '10px' }}>
                    <div style={{ flex: 1 }}>
                        <label style={{ display: 'block', marginBottom: '5px', fontWeight: 'bold' }}>Data do Evento:</label>
                        <input
                            type="date"
                            value={dataEvento}
                            onChange={(e) => setDataEvento(e.target.value)}
                            required
                            style={{ width: '100%', padding: '10px', borderRadius: '6px', border: '1px solid #ccc' }}
                        />
                    </div>
                    <div style={{ flex: 1 }}>
                        <label style={{ display: 'block', marginBottom: '5px', fontWeight: 'bold' }}>Status:</label>
                        <select
                            value={statusEvento}
                            onChange={(e) => setStatusEvento(e.target.value)}
                            style={{ width: '100%', padding: '10px', borderRadius: '6px', border: '1px solid #ccc', backgroundColor: '#fff' }}
                        >
                            <option value="ativo">Ativo</option>
                            <option value="cancelado">Cancelado</option>
                            <option value="encerrado">Encerrado</option>
                        </select>
                    </div>
                </div>

                <div style={{ display: 'flex', gap: '10px' }}>
                    <div style={{ flex: 1 }}>
                        <label style={{ display: 'block', marginBottom: '5px', fontWeight: 'bold' }}>Local:</label>
                        <select
                            value={localId}
                            onChange={(e) => setLocalId(e.target.value)}
                            required
                            style={{ width: '100%', padding: '10px', borderRadius: '6px', border: '1px solid #ccc', backgroundColor: '#fff' }}
                        >
                            <option value="">Selecione um local...</option>
                            {locais.map((local) => (
                                <option key={local.id} value={local.id}>
                                    {local.nome} (ID: {local.id})
                                </option>
                            ))}
                        </select>
                    </div>

                    <div style={{ flex: 1 }}>
                        <label style={{ display: 'block', marginBottom: '5px', fontWeight: 'bold' }}>Organizador:</label>
                        <select
                            value={organizadorId}
                            onChange={(e) => setOrganizadorId(e.target.value)}
                            required
                            style={{ width: '100%', padding: '10px', borderRadius: '6px', border: '1px solid #ccc', backgroundColor: '#fff' }}
                        >
                            <option value="">Selecione um organizador...</option>
                            {organizadores.map((org) => (
                                <option key={org.id} value={org.id}>
                                    {org.nome || `Organizador #${org.id}`}
                                </option>
                            ))}
                        </select>
                    </div>
                </div>

                <div>
                    <label style={{ display: 'block', marginBottom: '5px', fontWeight: 'bold' }}>Descrição:</label>
                    <textarea
                        value={descricao}
                        onChange={(e) => setDescricao(e.target.value)}
                        rows="3"
                        style={{ width: '100%', padding: '10px', borderRadius: '6px', border: '1px solid #ccc' }}
                    />
                </div>

                <button
                    type="submit"
                    style={{ padding: '12px', backgroundColor: '#8F9C3B', color: 'white', border: 'none', borderRadius: '6px', fontWeight: 'bold', cursor: 'pointer', fontSize: '16px' }}
                >
                    Guardar Evento
                </button>
            </form>
        </div>
    );
}

export default NovoRegisto;