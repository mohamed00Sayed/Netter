package com.sayed.netter.data;

import com.sayed.netter.Netter;

public interface NetterRepository {

	Netter save(Netter netter);

	Netter findByUsername(String username);

}
