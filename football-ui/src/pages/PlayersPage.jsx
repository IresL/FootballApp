import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { PlayersApi, FavoritesApi } from "../api/client.js";

export default function PlayersPage() {
  const { id } = useParams(); // teamId
  const [players, setPlayers] = useState([]);

  useEffect(() => {
    PlayersApi.listByTeam(id).then(setPlayers).catch(console.error);
  }, [id]);

  const addFav = async (pid) => {
    try {
      await FavoritesApi.add(pid);       // USER/ADMIN - authenticated
      alert("Added to favorites");
    } catch (e) {
      alert("Auth required (user/user123) or admin/admin123");
      console.error(e);
    }
  };

  const removePlayer = async (pid) => {
    if (!confirm("Delete player? (ADMIN only)")) return;
    try {
      await PlayersApi.remove(pid);      // ADMIN only (requires Admin Auth button pressed)
      setPlayers(prev => prev.filter(p => p.id !== pid));
    } catch (e) {
      alert("Admin auth required: press 'Admin Auth' in header");
      console.error(e);
    }
  };

  return (
    <>
      <h2>Players</h2>
      <ul>
        {players.map(p => (
          <li key={p.id}>
            #{p.number} {p.fullName} – {p.position} ({p.country}), age {p.age}, price {p.price}
            {" "}
            <button onClick={() => addFav(p.id)}>☆ Favorite</button>
            {" "}
            <button onClick={() => removePlayer(p.id)}>🗑 Delete</button>
          </li>
        ))}
      </ul>
    </>
  );
}
