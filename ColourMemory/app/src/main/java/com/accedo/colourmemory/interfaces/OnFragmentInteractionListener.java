package com.accedo.colourmemory.interfaces;

import com.accedo.colourmemory.MainActivity;

/**
 * Created by gabordudas on 23/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public interface OnFragmentInteractionListener {
    void onFragmentInteraction(String fragment, MainActivity.InteractionType type, Object... params);
}
