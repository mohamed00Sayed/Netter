package com.sayed.netter.data;

import com.sayed.netter.Nettle;

import java.util.List;

public interface NettleRepository {
	  List<Nettle> findRecentNettles();

	  List<Nettle> findNettles(long max, int count);
	  
	  Nettle findOne(long id);

	  void save(Nettle spittle);
}
