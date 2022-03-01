import axios from 'axios';
import React, { useState, useEffect } from 'react';

export const UsersPage = () => {
  const [users, setUsers] = useState([]);

  useEffect(async () => {
    const res = await axios.get('http://localhost:8080/users');
    setUsers(res.data);
  }, []);

  const getListedUsers = () => users.map(({ id }) => <li key={id}>{id}</li>);

  return (
    <div>
      <h1>Users</h1>
      <ul>{getListedUsers()}</ul>
    </div>
  );
};
