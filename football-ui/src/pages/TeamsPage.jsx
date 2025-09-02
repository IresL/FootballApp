import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { TeamsApi } from "../api/client.js";

export default function TeamsPage() {
  const { id } = useParams(); // championshipId
  const [teams, setTeams] = useState([]);

  useEffect(() => {
    TeamsApi.listByChampionship(id).then(setTeams).catch(console.error);
  }, [id]);

  return (
    <>
      <h2>Teams</h2>
      <ul>
        {teams.map(t => (
          <li key={t.id}>
            <Link to={`/teams/${t.id}/players`}>{t.name} (titles: {t.titlesCount})</Link>
            {" "}
            <Link to={`/admin/teams/${t.id}/players/new`}>[+ New Player]</Link>
          </li>
        ))}
      </ul>
    </>
  );
}
