import { useEffect, useState } from "react";
import { FavoritesApi } from "../api/client.js";

export default function FavoritesPage() {
  const [items, setItems] = useState([]);

  useEffect(() => {
    FavoritesApi.list().then(setItems).catch(e => {
      alert("Auth required (user/user123 or admin/admin123). Click 'Admin Auth' for admin or use an interceptor.");
      console.error(e);
    });
  }, []);

  const removeFav = async (pid) => {
    await FavoritesApi.remove(pid);
    setItems(prev => prev.filter(p => p.id !== pid));
  };

  return (
    <>
      <h2>Favorites</h2>
      <ul>
        {items.map(p => (
          <li key={p.id}>
            #{p.number} {p.fullName} – {p.position} ({p.country})
            {" "}
            <button onClick={() => removeFav(p.id)}>Remove</button>
          </li>
        ))}
      </ul>
    </>
  );
}
