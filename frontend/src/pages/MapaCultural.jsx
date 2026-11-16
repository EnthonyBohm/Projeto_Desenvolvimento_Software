import { useState, useEffect } from 'react';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import { api } from '../services/api';

function MapaCultural() {
    const [eventos, setEventos] = useState([]);

    useEffect(() => {
        api.get('/eventos')
            .then((response) => {
                setEventos(response.data);
            })
            .catch((error) => {
                console.error("Erro ao carregar eventos para o mapa:", error);
            });
    }, []);

    // coordenadas padrão centralizadas (ex: Pelotas, RS) caso o evento não traga lat/long exatas
    const posicaoCentral = [-31.7654, -52.3376];

    return (
        <div style={{ padding: '20px' }}>
            <h2>Mapa de Eventos Culturais</h2>
            <p>Explore os eventos culturais disponíveis na região diretamente no mapa interativo.</p>

            <div style={{ height: '500px', width: '100%', borderRadius: '12px', overflow: 'hidden', border: '2px solid #8F9C3B', marginTop: '20px' }}>
                <MapContainer center={posicaoCentral} zoom={13} style={{ height: '100%', width: '100%' }}>
                    <TileLayer
                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    />

                    {eventos.map((evento) => {
                        // se o backend retornar latitude e longitude usamos aqui
                        // caso contrário usamos coordenadas de teste próximas pro marcador aparecer.
                        const lat = evento.latitude || -31.7654;
                        const lng = evento.longitude || -52.3376;

                        return (
                            <Marker key={evento.id} position={[lat, lng]}>
                                <Popup>
                                    <strong>{evento.nome}</strong><br />
                                    {evento.descricao}
                                </Popup>
                            </Marker>
                        );
                    })}
                </MapContainer>
            </div>
        </div>
    );
}

export default MapaCultural;