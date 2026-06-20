import { useNavigate } from "react-router-dom";
import { logout } from "../services/authService";

export default function HomePage() {
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <div className="min-h-screen bg-zinc-950 text-white">
      <nav className="bg-zinc-900 px-6 py-4 flex justify-between items-center">
        <h1 className="text-green-500 font-bold text-xl">Noryx</h1>
        <button
          onClick={handleLogout}
          className="text-zinc-400 hover:text-white transition-colors text-sm"
        >
          Logout
        </button>
      </nav>
      <main className="p-6">
        <h2 className="text-2xl font-bold">Welcome to Noryx</h2>
      </main>
    </div>
  );
}
