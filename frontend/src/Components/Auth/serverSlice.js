import { createSlice } from '@reduxjs/toolkit';

export const serverSlice = createSlice({
  name: 'server',
  initialState: {
    activeServer: {
      id: '',
      name: '',
      url: '',
      port: '',
    },
    servers: [
      {
        id: '1',
        name: 'Cloud-STM',
        url: 'http://jira.cloud-stm.com:8080',
        port: '8080',
      },
      {
        id: '2',
        name: 'Cloud-MKT',
        url: 'http://jira.cloud-mkt.com:8080',
        port: '8080',
      },
    ],
    projects: [
      {
        id: '',
        name: '',
        type: '',
        lead: '',
        image: '',
        created: '',
        issues: '',
      },
    ],
  },
  reducers: {
    setActiveServer: (state, action) => {
      state.activeServer.url = action.payload;
    },
    addServer(state, action) {
      state.servers.push(action.payload);
    },
    removeServerById(state, action) {
      state.server = state.servers.filter(
        (server) => server.id !== action.payload,
      );
    },
  },
});

export const { setActiveServer, addServer } = serverSlice.actions;
export default serverSlice.reducer;
