package cl.chileindica.ticket;

import java.util.Map;

import cl.chileindica.util.CONSTANTS;
import cl.chileindica.util.map.Mapper;
import cl.chileindica.util.map.Reducer;
import cl.chileindica.util.map.SQLMapper;

public class Ticket161055 implements Ticket{

	public static void main(String args[]) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		
	
	}
	
	public String defineProblem() {
		String sql = "SELECT '[id]' ID ,A.C_INSTRUMENTO,I.N_INSTRUMENTO FROM [db].VIGENCIA_INSTRUMENTO A left join [db].VIGENCIA_INSTRUMENTO B on ( A.C_INSTRUMENTO=B.C_INSTRUMENTO AND B.ANO_VIGENCIA=A.ANO_VIGENCIA+1)  ";
		sql += "LEFT JOIN [db].INSTRUMENTO I ON (I.C_INSTRUMENTO=A.C_INSTRUMENTO) ";
		sql += "WHERE A.ANO_VIGENCIA=2015 AND B.ANO_VIGENCIA IS NULL";
		return sql;
	}

	public Mapper defineMapper() throws Exception {

		Mapper mapper=new SQLMapper();
		return mapper;
		
	}
	
	public Reducer defineReducer(){
		return new Reducer(){

			public void reduce(Map item) {
				Integer i = Integer.parseInt((String) item.get("ID"));
				System.out.print(CONSTANTS.db[i.intValue()] + ":");
				System.out.print(item.get("C_INSTRUMENTO"));
				System.out.print(":");
				System.out.println(item.get("N_INSTRUMENTO"));
			}
			
		};
		
	}

}
