import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class MyCITS2200Project implements CITS2200Project {
	private ArrayList<Vertex> vertices;

	private class Vertex{
		String url;
		ArrayList<Vertex> neighbour;
		int distance;
		
		private Vertex(String url) {
			this.url = url;
			neighbour = new ArrayList<Vertex>();
		}
		
		private void addEdge(Vertex v) {
			neighbour.add(v);
		}
		
	}
	
	public MyCITS2200Project() {
		vertices = new ArrayList<Vertex>();
	}
	/**
	 * Linking two Wikipedia page use url, if url both exist added in 
	 * ArrayList. If not exist, create a new one.
	 * @parm String urlFrom input a web address that starting from
	 * @parm String urlTo input a web address that end to
	 */
	public void addEdge(String urlFrom, String urlTo) {
		boolean findingFrom = false;
		boolean findingTo = false;
		Vertex url1 = null;
		Vertex url2 = null;
		Iterator<Vertex> checkUrl = vertices.iterator();
		
		//Iterating whether if two url exist in graph, otherwise create a new and one added into ArrayList
		while((!findingFrom || !findingTo) && checkUrl.hasNext()) {
			Vertex current = checkUrl.next();
			
			if(current.url.equals(urlFrom)) {
				findingFrom = true;
				url1 = current;
			}
			
			if(current.url.equals(urlTo)) {
				findingTo = true;
				url2 = current;
			}
		}
			if(!findingFrom) {
				url1 = new Vertex(urlFrom);
				vertices.add(url1);
			}
			if(!findingTo && !urlFrom.equals(urlTo)) {
				url2 = new Vertex(urlTo);
				vertices.add(url2);
			}else if(urlFrom.equals(urlTo)) {
				url2 = url1;
			}
		url1.addEdge(url2);
	}

	/**
	 * get the shortest distance between two web pages using BFS(unweighted, directed)
	 * @parm String urlFrom input a web address that starting from
	 * @parm String urlTo input a web address that end to
	 * @return return -1 if vertices not connected otherwise get shortest distance
	 */
	public int getShortestPath(String urlFrom, String urlTo) {
		Vertex startV = null;
		//initalize all value to -1
		for(Vertex v: vertices) {
			v.distance = -1;
			//check if urlFrom or ulrTo exist in graph
			if((v.url).equals(urlFrom) || (v.url).equals(urlTo)) {
				if((v.url).equals(urlFrom)) {
					startV = v;
					startV.distance = 0;
				}
			}
		}
		
		//Breath First Search
		Queue<Vertex> que = new LinkedList<Vertex>();
		que.add(startV);
		while(!que.isEmpty()) {
			Vertex current = que.poll();
			for(Vertex v: current.neighbour) {
				if(v.distance == -1) {
					v.distance = current.distance + 1;
					que.add(v);
				}
			}
			if((current.url).equals(urlTo)) {
				return current.distance;
			}
		}
		
		return -1;
	}

	@Override
	public String[] getCenters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getStronglyConnectedComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getHamiltonianPath() {
		// TODO Auto-generated method stub
		return null;
	}

}
