import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { PlayersApi } from "../api/client.js";

export default function AdminNewPlayerPage() {
  const { id } = useParams(); // teamId
  const nav = useNavigate();
  const [form, setForm] = useState({
    fullName: "",
    number: 10,
    position: "FW",
    age: 25,
    country: "IT",
    price: 1000000.00
  });

  const onChange = (e) => {
    const { name, value } = e.target;
    setForm(prev => ({ ...prev, [name]: name === "price" || name === "number" || name === "age" ? Number(value) : value }));
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      await PlayersApi.create(id, form); // ADMIN only: header-ში დააჭირე "Admin Auth"
      nav(`/teams/${id}/players`);
    } catch (e) {
      alert("Admin auth required: click 'Admin Auth' in header");
      console.error(e);
    }
  };

  return (
    <>
      <h2>New Player</h2>
      <form onSubmit={onSubmit} style={{ display: "grid", gap: 8, maxWidth: 420 }}>
        <input name="fullName" placeholder="Full name" value={form.fullName} onChange={onChange}/>
        <input name="number" type="number" placeholder="Number" value={form.number} onChange={onChange}/>
        <input name="position" placeholder="Position (GK/DF/MF/FW)" value={form.position} onChange={onChange}/>
        <input name="age" type="number" placeholder="Age" value={form.age} onChange={onChange}/>
        <input name="country" placeholder="Country" value={form.country} onChange={onChange}/>
        <input name="price" type="number" step="0.01" placeholder="Price" value={form.price} onChange={onChange}/>
        <button type="submit">Create</button>
      </form>
    </>
  );
}
