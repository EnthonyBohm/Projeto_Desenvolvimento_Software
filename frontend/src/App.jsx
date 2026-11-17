import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Eventos from './pages/Eventos';
import './App.css';
import MapaCultural from './pages/MapaCultural';
import NovoRegistro from './pages/NovoRegistro';
import ArtistasLocais from './pages/ArtistasLocais';

function App() {
    return (
        <BrowserRouter>
            {/* Barra de Navegação */}
            <nav style={{ padding: '20px', backgroundColor: '#8F9C3B', display: 'flex', gap: '20px' }}>
                <Link to="/" style={{ color: 'white', textDecoration: 'none', fontWeight: 'bold' }}>Eventos</Link>
                <Link to="/artistas-locais" style={{ color: 'white', textDecoration: 'none', fontWeight: 'bold' }}>Artistas & Locais</Link>
                <Link to="/mapa" style={{ color: 'white', textDecoration: 'none', fontWeight: 'bold' }}>Mapa Cultural</Link>
                <Link to="/cadastro" style={{ color: 'white', textDecoration: 'none', fontWeight: 'bold' }}>Novo Registo</Link>
            </nav>

            {/* Gestor de Páginas */}
            <div style={{ padding: '20px' }}>
                <Routes>
                    <Route path="/" element={<Eventos />} />
                    <Route path="/artistas-locais" element={<ArtistasLocais />} />
                    <Route path="/mapa" element={<MapaCultural />} />
                    <Route path="/cadastro" element={<NovoRegistro />} />
                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;