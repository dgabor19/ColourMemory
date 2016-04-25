package com.accedo.colourmemory.interfaces;

/**
 * Created by gabordudas on 23/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */

import com.accedo.colourmemory.MainActivity;

public interface OnFragmentInteractionListener {
    void onFragmentInteraction(String fragment, MainActivity.InteractionType type, Object... params);
}
