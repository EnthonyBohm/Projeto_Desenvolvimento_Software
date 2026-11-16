import { useState, useEffect } from 'react';
import { api } from '../services/api';

function Eventos() {
    // armazenar a lista de eventos que vem do backend
    const [eventos, setEventos] = useState([]);

    useEffect(() => {
        // faz a chamada GET à API assim que o componente é montado na tela
        api.get('/eventos')
            .then((response) => {
                setEventos(response.data);
            })
            .catch((error) => {
                console.error("Erro ao carregar os eventos:", error);
            });
    }, []);

    return (
        <div style={{ padding: '20px', fontFamily: 'sans-serif' }}>
            <h1>Mapa Cultural</h1>
            <h2>Eventos Disponíveis</h2>

            <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
                {eventos.length > 0 ? (
                    eventos.map((evento) => (
                        <div
                            key={evento.id}
                            style={{
                                border: '1px solid #ccc',
                                padding: '15px',
                                borderRadius: '8px',
                                backgroundColor: '#fff'
                            }}
                        >
                            {/* adaptar os nomes dos campos (nome, data) conforme o DTO do Enthony */}
                            <h3>{evento.nome || 'Nome do Evento Indisponível'}</h3>
                            <p><strong>Descrição:</strong> {evento.descricao || 'Sem descrição'}</p>
                        </div>
                    ))
                ) : (
                    <p>A procurar eventos no banco de dados...</p>
                )}
            </div>
        </div>
    );
}

export default Eventos;