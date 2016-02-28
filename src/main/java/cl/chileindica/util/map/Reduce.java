package cl.chileindica.util.map;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Reduce {

	public void reduce(Reducer reducer, List list ){
		Iterator it=list.iterator();
		while(it.hasNext()){
			List l=(List)it.next();
			Iterator itt=l.iterator();
			while(itt.hasNext()){
				Map map=(Map)itt.next();
				
				reducer.reduce(map);

				
			}
			
		}
	}
	


}
