package cl.chileindica.ticket;

import java.util.Map;

import cl.chileindica.util.CONSTANTS;
import cl.chileindica.util.map.Executor;
import cl.chileindica.util.map.Mapper;
import cl.chileindica.util.map.SQLMapper;
import cl.chileindica.util.map.Reducer;

public class Ticket888306 implements Ticket{
	
	public static void main(String args[]) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		Executor.execute(Ticket888306.class.newInstance());
	
	}
	
	public String defineProblem() {

		String sql = "SELECT DISTINCT '[id]' ID,N_INSTITUCION,USUARIO,NOMBRES,APELLIDO_PATERNO,APELLIDO_MATERNO,MAIL,VIGENTE,MULTIPLE FROM [db].ADMIN_USUARIO U, [db].INSTITUCION I, [db].ADMIN_USUARIO_PERFIL P  WHERE U.C_INSTITUCION=I.C_INSTITUCION AND P.C_USUARIO=U.C_USUARIO AND N_INSTITUCION LIKE '%Agricultura%'";
		return sql;
	}

	public Mapper defineMapper() throws Exception {
		Mapper mapper=new SQLMapper();
		return mapper;
	}

	public Reducer defineReducer(){
		return new Reducer(){
			
			

			public void reduce(Map item) {
				
				String SEPARADOR=";";
				
				Integer i = Integer.parseInt((String) item.get("ID"));
				System.out.print(CONSTANTS.db[i.intValue()]);
				System.out.print(SEPARADOR);
				System.out.print(item.get("N_INSTITUCION"));
				System.out.print(SEPARADOR);

				System.out.print(item.get("USUARIO"));
				
				System.out.print(SEPARADOR);
				System.out.print(item.get("NOMBRES"));
				System.out.print(SEPARADOR);
				System.out.print(item.get("APELLIDO_PATERNO"));
				System.out.print(SEPARADOR);
				System.out.print(item.get("APELLIDO_MATERNO"));
				System.out.print(SEPARADOR);
				System.out.print(item.get("MAIL"));
				System.out.print(SEPARADOR);
				System.out.print(item.get("VIGENTE").equals("S")?"VIGENTE":"NO VIGENTE");
				System.out.print(SEPARADOR);
				System.out.print(item.get("MULTIPLE").equals("S")?"MULTIPLE":"NO MULTIPLE");
				System.out.println();


			}
			
		};
		
	}
	
	
	
	

}
