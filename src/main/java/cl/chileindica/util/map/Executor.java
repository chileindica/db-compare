package cl.chileindica.util.map;

import java.util.*;

import cl.chileindica.ticket.Ticket;
import cl.chileindica.ticket.Ticket161055;
import cl.chileindica.util.CONSTANTS;
import cl.chileindica.util.map.Reducer;

public class Executor {


	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void execute(Ticket ticket)throws Exception {
	
		String sql = ticket.defineProblem();
		List list = new LinkedList();

		for (int i = 0; i < CONSTANTS.db.length; i++) {
			Mapper mapper=ticket.defineMapper();
			String context = sql.replace("[id]", String.valueOf(i)).replace("[db]", CONSTANTS.db[i]);
			list.add(mapper.process(context));
		}

		Reducer reducer=ticket.defineReducer();
		Reduce reduce = new Reduce();
		reduce.reduce(reducer,list);
		
	}
	

	public static void main(String args[]) throws Exception {
		
		Ticket ticket=new Ticket161055();
		execute(ticket);
		
		
		

	}


	





}
