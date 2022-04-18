import { createSlice } from '@reduxjs/toolkit';

export const userSlice = createSlice({
  name: 'user',
  initialState: {
    isUserSignedIn: false,
    user: {
      id: '',
      name: '',
      email: '',
      role: '',
    },
  },
  reducers: {
    signinUser: (state, action) => {
      state.isUserSignedIn = true;
      state.user = action.payload;
    },
    signoutUser: (state) => {
      state.isUserSignedIn = false;
      state.user = {
        id: '',
        name: '',
        email: '',
        role: '',
      };
    },
    updateUser: (state, action) => {
      state.user = action.payload;
    },
  },
});

export const { signinUser, signoutUser, updateUser } = userSlice.actions;
export default userSlice.reducer;
