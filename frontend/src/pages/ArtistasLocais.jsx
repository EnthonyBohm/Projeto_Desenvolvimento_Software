import { useState, useEffect } from 'react';
import { api } from '../services/api';

function ArtistasLocais() {
    const [artistas, setArtistas] = useState([]);
    const [locais, setLocais] = useState([]);

    useEffect(() => {
        api.get('/artistas')
            .then((res) => setArtistas(res.data))
            .catch((err) => console.error('Erro ao carregar artistas:', err));

        api.get('/locais')
            .then((res) => setLocais(res.data))
            .catch((err) => console.error('Erro ao carregar locais:', err));
    }, []);

    return (
        <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '25px' }}>
            {/* Coluna de Artistas */}
            <div>
                <h2>Artistas Cadastrados</h2>
                <div style={{ display: 'flex', flexDirection: 'column', gap: '12px' }}>
                    {artistas.map((artista) => (
                        <div key={artista.id} style={{ backgroundColor: 'white', padding: '15px', borderRadius: '10px' }}>
                            <h3 style={{ margin: '0 0 5px 0', color: '#1F3318' }}>
                                {artista.nomeArtistico || artista.nome}
                            </h3>
                            {artista.generoMusical && <p style={{ margin: 0 }}><strong>Género/Área:</strong> {artista.generoMusical}</p>}
                            {artista.biografia && <p style={{ margin: '5px 0 0 0', fontSize: '14px' }}>{artista.biografia}</p>}
                        </div>
                    ))}
                </div>
            </div>

            {/* Coluna de Locais */}
            <div>
                <h2>Locais Culturais</h2>
                <div style={{ display: 'flex', flexDirection: 'column', gap: '12px' }}>
                    {locais.map((local) => (
                        <div key={local.id} style={{ backgroundColor: 'white', padding: '15px', borderRadius: '10px' }}>
                            <h3 style={{ margin: '0 0 5px 0', color: '#1F3318' }}>{local.nome}</h3>
                            {local.endereco && <p style={{ margin: 0 }}><strong>Endereço:</strong> {local.endereco}</p>}
                            {local.capacidade && <p style={{ margin: '5px 0 0 0', fontSize: '14px' }}><strong>Capacidade:</strong> {local.capacidade} pessoas</p>}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default ArtistasLocais;