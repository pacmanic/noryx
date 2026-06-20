const API_URL = "http://localhost:8080/api/auth";

export interface AuthResponse{
  token: string;
}

export const register = async(
  username: string,
  email: string,
  password: string,
): Promise<AuthResponse> => {
  const response = await fetch(`${API_URL}/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, email, password }),
  });

  if(!response.ok){
    throw new Error("Registration failed");
  }

  return response.json();
};

export const login = async (
  username: string,
  password: string,
): Promise<AuthResponse> => {
  const response = await fetch(`${API_URL}/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password }),
  });

  if(!response.ok){
    throw new Error("Login failed");
  }

  return response.json();
};

export const saveToken = (token: string) => {
  localStorage.setItem("token", token);
};

export const getToken = (): string | null => {
  return localStorage.getItem("token");
};

export const logout = () => {
  localStorage.removeItem("token");
};
