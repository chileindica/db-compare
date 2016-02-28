package cl.chileindica.ticket;

import cl.chileindica.util.map.Mapper;
import cl.chileindica.util.map.Reducer;

public interface Ticket {
	public String defineProblem();

	public Mapper defineMapper() throws Exception;

	public Reducer defineReducer();
}
