import { useEffect, useState } from "react";
import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API, // http://localhost:8080
});

export default function App() {
  const [items, setItems] = useState([]);
  const [error, setError] = useState("");
  const [name, setName] = useState("");

  useEffect(() => {
    api.get("/api/championships")
      .then(res => setItems(res.data))
      .catch(err => {
        console.error(err);
        setError("ვერ დავუკავშირდი backend-ს");
      });
  }, []);

  const onCreate = async (e) => {
    e.preventDefault();
    if (!name.trim()) return;
    try {
      const res = await api.post("/api/championships", { name: name.trim() });
      setItems(prev => [...prev, res.data]); // სიაში დაამატე
      setName("");
    } catch (err) {
      console.error(err);
      alert("შექმნა ვერ შესრულდა");
    }
  };

  return (
    <div style={{ padding: 16 }}>
      <h1>Championships</h1>
      {error && <div style={{ color: "tomato" }}>{error}</div>}

      {items.length === 0 ? (
        <p>ცარიელია (შეიძლება ჯერ არაფერი გაქვს შექმნილი).</p>
      ) : (
        <ul>
          {items.map(c => <li key={c.id}>{c.name}</li>)}
        </ul>
      )}

      <form onSubmit={onCreate} style={{ marginTop: 16, display: "flex", gap: 8 }}>
        <input
          placeholder="New Championship Name"
          value={name}
          onChange={e => setName(e.target.value)}
        />
        <button type="submit">add</button>
      </form>
    </div>
  );
}
