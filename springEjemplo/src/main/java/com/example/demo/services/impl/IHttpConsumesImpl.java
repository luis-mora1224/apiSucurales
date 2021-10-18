package com.example.demo.services.impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.example.demo.models.Dato;
import com.example.demo.models.Servicio;
import com.example.demo.services.IHttpConsumes;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;


@Service
public class IHttpConsumesImpl implements IHttpConsumes{
	
	
	@Override
	public String sendGet() {
		String respuesta = "";
		String cadena="";
		try{

		String USER_AGENT = "Mozilla/5.0";
		String url = "https://www.banamex.com/localizador/jsonP/json5.json";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// el valor predeterminado es get
		con.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while((inputLine = in.readLine()) !=null){
			response.append(inputLine);
		}
		in.close();

		respuesta = response.toString();
		
		cadena = respuesta.replaceAll("jsonCallback","");
		respuesta = cadena.replaceAll(";","");
		String str = respuesta.replaceAll("\\(", "").replaceAll("\\)","");
		respuesta = str;
		
        JsonNode jsonNode = Json.parse(str);
		
        /*Iterator<Entry<String, JsonNode>> it = jsonNode.fields();
        while (it.hasNext()) {      
        	  Entry<String, JsonNode> field = it.next();
              System.out.println(field.getKey() +" " + field.getValue() + "\n");
        }*/
        
		
		if(jsonNode.has("Servicios")) {
		    List<String> list = new ArrayList<>();
		    List<Dato> datos = new ArrayList<>();
		    Dato dato = new Dato();
		    
		    
		    int ld1 = jsonNode.get("Servicios").get("300").size();
		    int ld2 = jsonNode.get("Servicios").get("300").get("0").size();
		    
		    for(int i = 0; i<=0; i++) {
		    	for(int j=1; j<=3; j++) {
		    		
		    		//jsonNode.get("Servicios").get("100").get(String.valueOf(i)).get(String.valueOf(j)).forEach(node -> !node.isEmpty() ? list.add(node.asText()));
		    	
		    			jsonNode.get("Servicios").get("100").get(String.valueOf(i)).get(String.valueOf(j)).forEach(node -> list.add(node.asText()));
			    		
			    		
					    dato.setId(Integer.parseInt(list.get(0)));
					    dato.setNumServicio(Integer.parseInt(list.get(1)));
					    dato.setCiudad(list.get(2));
					    dato.setCalle(list.get(3));
					    dato.setColonia(list.get(4));
					    dato.setNumCajas(Integer.parseInt(list.get(5)));
					    dato.setCalleIntersepcion1(list.get(6));
					    dato.setCalleIntersepcion1(list.get(7));
					    dato.setTelefono(list.get(8));
						dato.setParamDes(list.get(9));
						dato.setParamDes1(list.get(10));
						dato.setParamDes2(list.get(11));
						dato.setCodActividad(list.get(12));
						dato.setHoraApertura(list.get(13));
						dato.setHoraCierre(list.get(14));
						dato.setCoordLogitud(list.get(15));
						dato.setCoordLatitud(list.get(16));
						dato.setEstado(list.get(17));
						dato.setParamDes3(list.get(18));
						dato.setTipoServicio(list.get(19));
						dato.setParamDe4(list.get(20));
						dato.setParamDe5(list.get(21));
						dato.setParamDe6(list.get(22));
						
						datos.add(dato);
						
		    		
		    		
		    		
		    	}
		    }
		    
		  //jsonNode.get("Servicios").get("100").get("0").get("1").forEach(node -> list.add(node.asText()));
		    //jsonNode.get("Servicios").get("300").get("0").get("9997").forEach(node -> list.add(node.asText()));
		    /*for (String country : list) {
	            System.out.println(country);
	        }*/
		    
		  //String res = str.replaceAll("\\{\"Servicios\":","").replaceAll("\\}}", "");
			/*JSONObject objetoJson = new JSONObject(str);
			respuesta = objetoJson.getJSONObject("Servicios").getJSONObject("100").getJSONObject("0").getJSONArray("1").getString(4);*/
			// Y ahora podemos acceder a través de getTipoDeDato
			//System.out.println(objetoJson.get("100"));
			//int servicios = objetoJson.getString("Servicios");
			//String Ser = objetoJson.getString("100");
			
		  //process("Sucursales cercanas", jsonNode);
		    
			for (Dato info : datos) {
	            System.out.println(info);
	        }
			
			for (String lst : list) {
	            System.out.println(lst);
	        }
		  }
		
		
		}catch(IOException ex){
			ex.printStackTrace();
		}
		return respuesta;
		//return dato;
	}
	
	
	@Override
	public void process(String prefix, JsonNode currentNode) {
	    if (currentNode.isArray()) {
	        ArrayNode arrayNode = (ArrayNode) currentNode;
	        Iterator<JsonNode> node = arrayNode.elements();
	        int index = 1;
	        while (node.hasNext()) {
	            process(!prefix.isEmpty() ? prefix + "-" + index : String.valueOf(index), node.next());
	            index += 1;
	        }
	    }
	    else if (currentNode.isObject()) {
	        currentNode.fields().forEachRemaining(entry -> process(!prefix.isEmpty() ? prefix + "-" + entry.getKey() : entry.getKey(), entry.getValue()));
	    }
	    else {
	        System.out.println(prefix + ": " + currentNode.toString());
	    }
	}

}