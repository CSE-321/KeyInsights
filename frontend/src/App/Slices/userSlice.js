import { createSlice } from '@reduxjs/toolkit';

export const userSlice = createSlice({
  name: 'user',
  initialState: {
    isUserSignedIn: false,
  },
  reducers: {
    signinUser: (state) => {
      state.isUserSignedIn = true;
    },
    signoutUser: (state) => {
      state.isUserSignedIn = false;
    },
    updateUser: (state, action) => {
      state.user = action.payload;
    },
  },
});

export const { signinUser, signoutUser, updateUser } = userSlice.actions;
export default userSlice.reducer;
