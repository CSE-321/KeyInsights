import { createSlice } from '@reduxjs/toolkit';

export const tokensSlice = createSlice({
  name: 'tokens',
  initialState: {
    accessToken: '',
    refreshToken: '',
  },
  reducers: {
    setAccessToken: (state, action) => {
      state.accessToken = action.payload;
    },
    setRefreshToken: (state, action) => {
      state.refreshToken = action.payload;
    },
  },
});

export const { setAccessToken, setRefreshToken } = tokensSlice.actions;
export default tokensSlice.reducer;
