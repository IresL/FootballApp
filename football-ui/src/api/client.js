// src/api/client.js
import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API, // http://localhost:8080
  withCredentials: false,
});

// სურვილით: დააყენე Basic Auth მხოლოდ მაშინ, როცა ADMIN ოპერაციებს აკეთებ
export function setAdminAuth() {
  const token = btoa("admin:admin123"); // მხოლოდ dev-ში
  api.defaults.headers.common["Authorization"] = `Basic ${token}`;
}

export function clearAuth() {
  delete api.defaults.headers.common["Authorization"];
}

// ---- API helper-ები ----
export const ChampionshipsApi = {
  list: () => api.get("/api/championships").then(r => r.data),
  create: (name) => api.post("/api/championships", { name }).then(r => r.data),
};

export const TeamsApi = {
  listByChampionship: (champId) => api.get(`/api/championships/${champId}/teams`).then(r => r.data),
};

export const PlayersApi = {
  listByTeam: (teamId) => api.get(`/api/teams/${teamId}/players`).then(r => r.data),
  create: (teamId, payload) => api.post(`/api/teams/${teamId}/players`, payload).then(r => r.data), // ADMIN only
  remove: (playerId) => api.delete(`/api/players/${playerId}`).then(r => r.status === 204),        // ADMIN only
};

export const FavoritesApi = {
  add: (playerId) => api.post(`/api/favorites/players/${playerId}`).then(r => r.data),
  list: () => api.get(`/api/favorites/players`).then(r => r.data),
  remove: (playerId) => api.delete(`/api/favorites/players/${playerId}`).then(r => r.status === 204),
};

export default api;
