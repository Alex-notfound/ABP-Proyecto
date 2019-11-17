//package com.padelclub.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//
//@Entity
//public class Sorteo<Enfrentamiento>{
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	@ManyToOne
//	public Enfrentamiento[][] rondas;
//
//	public void sorteoLiga(int numEquipos) {
//		
//		
//		int rondas = numEquipos - numEquipos % 2;
//		int partidos = numEquipos / 2;
//		List<UsuarioCampeonatoId> parejas = new ArrayList<>();
//		List<Enfrentamiento> enfrentamientos = new ArrayList<>();
//
//		List<Integer> numSorteo = new ArrayList<>();
//
//		boolean esta = false;
//		for (int i = 0; i < numEquipos; i++) {
//
//			do {
//				int aux = (int) (numEquipos * Math.random());
//				if (numSorteo.contains(aux)) {
//					esta = true;
//				}else {
//					numSorteo.add(aux);
//				}
//			} while (esta);
//		}
//		
//	}
//	
//
//	
//	
//	
//	
//	private List<Integer> siguienteJornada(List<Integer> numSorteo) {
//		
//		int aux = numSorteo.remove(numSorteo.size()-1);
//		numSorteo.add(0, aux);
//		return numSorteo;
//	}
//		
//	
//	
//	private List<Integer> colocarEnfrentamiento(List<Integer> numSorteo, int partidos){
//	int numPartido[][] = new int[partidos][2];
//	int j=0;
//	for(int i=0;i<partidos;i++) {
//		numPartido[i][0]=numSorteo.get(j);
//		j++;
//		numPartido[i][1]=numSorteo.get(j);
//		j++;
//	}
//	return numSorteo;
//	}
//	
//}

