import { useEffect, useState } from "react";
import { ChampionshipsApi } from "../api/client.js";
import { Link } from "react-router-dom";

export default function ChampionshipsPage() {
  const [items, setItems] = useState([]);
  const [name, setName] = useState("");

  useEffect(() => {
    ChampionshipsApi.list().then(setItems).catch(console.error);
  }, []);

  const onCreate = async (e) => {
    e.preventDefault();
    if (!name.trim()) return;
    const created = await ChampionshipsApi.create(name.trim());
    setItems(prev => [...prev, created]);
    setName("");
  };

  return (
    <>
      <h2>Championships</h2>
      <ul>
        {items.map(c => (
          <li key={c.id}>
            <Link to={`/championships/${c.id}/teams`}>{c.name}</Link>
          </li>
        ))}
      </ul>

      <form onSubmit={onCreate} style={{ marginTop: 16 }}>
        <input
          placeholder="New championship name"
          value={name}
          onChange={e => setName(e.target.value)}
        />
        <button type="submit">Create</button>
      </form>
    </>
  );
}
