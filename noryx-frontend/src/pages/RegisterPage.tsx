import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { register, saveToken } from "../services/authService";

export default function RegisterPage() {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");
    try {
      const response = await register(username, email, password);
      saveToken(response.token);
      navigate("/");
    } catch {
      setError("Registration failed");
    }
  };

  return (
    <div className="min-h-screen bg-zinc-950 flex items-center justify-center">
      <div className="bg-zinc-900 p-8 rounded-2xl w-full max-w-md">
        <h1 className="text-white text-2xl font-bold mb-6">Create Account</h1>
        <form onSubmit={handleSubmit} className="flex flex-col gap-4">
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="bg-zinc-800 text-white placeholder-zinc-500 px-4 py-3 rounded-lg outline-none focus:ring-2 focus:ring-green-500"
          />
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="bg-zinc-800 text-white placeholder-zinc-500 px-4 py-3 rounded-lg outline-none focus:ring-2 focus:ring-green-500"
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="bg-zinc-800 text-white placeholder-zinc-500 px-4 py-3 rounded-lg outline-none focus:ring-2 focus:ring-green-500"
          />
          {error && <p className="text-red-500 text-sm">{error}</p>}
          <button
            type="submit"
            className="bg-green-500 hover:bg-green-400 text-black font-semibold py-3 rounded-lg transition-colors"
          >
            Register
          </button>
        </form>
        <p className="text-zinc-500 text-sm mt-4 text-center">
          Already have an account?{" "}
          <a href="/login" className="text-green-500 hover:underline">
            Login
          </a>
        </p>
      </div>
    </div>
  );
}
