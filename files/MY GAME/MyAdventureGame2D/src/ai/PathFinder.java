package ai;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;

public class PathFinder {
	GamePanel gp;

	Node[][] node;

	ArrayList<Node> openList = new ArrayList<>();

	public ArrayList<Node> pathList = new ArrayList<>();

	Node startNode;

	Node goalNode;

	Node currentNode;

	boolean goalReached = false;

	int step = 0;

	public PathFinder(GamePanel gp) {
		this.gp = gp;
		instantiateNodes();
	}

	public void instantiateNodes() {
		this.node = new Node[this.gp.maxWorldCol][this.gp.maxWorldRow];
		int col = 0;
		int row = 0;
		while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
			this.node[col][row] = new Node(col, row);
			col++;
			if (col == this.gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}

	public void resetNodes() {
		int col = 0;
		int row = 0;
		while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
			(this.node[col][row]).open = false;
			(this.node[col][row]).checked = false;
			(this.node[col][row]).solid = false;
			col++;
			if (col == this.gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		this.openList.clear();
		this.pathList.clear();
		this.goalReached = false;
		this.step = 0;
	}

	public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity entity) {
		resetNodes();
		this.startNode = this.node[startCol][startRow];
		this.currentNode = this.startNode;
		this.goalNode = this.node[goalCol][goalRow];
		this.openList.add(this.currentNode);
		int col = 0;
		int row = 0;
		while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
			int tileNum = this.gp.tileM.mapTileNum[this.gp.currentMap][col][row];
			if ((this.gp.tileM.tile[tileNum]).collision)
				(this.node[col][row]).solid = true;
			getCost(this.node[col][row]);
			col++;
			if (col == this.gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		for (int i = 0; i < (this.gp.iTile[1]).length; i++) {
			if (this.gp.iTile[this.gp.currentMap][i] != null && (this.gp.iTile[this.gp.currentMap][i]).destructible) {
				
				int itCol = (this.gp.iTile[this.gp.currentMap][i]).worldX / 48;
				
				int itRow = (this.gp.iTile[this.gp.currentMap][i]).worldY / 48;
				(this.node[itCol][itRow]).solid = true;
			}
		}
	}

	public void getCost(Node node) {
		int xDistance = Math.abs(node.col - this.startNode.col);
		int yDistance = Math.abs(node.row - this.startNode.row);
		node.gCost = xDistance + yDistance;
		xDistance = Math.abs(node.col - this.goalNode.col);
		yDistance = Math.abs(node.row - this.goalNode.row);
		node.hCost = xDistance + yDistance;
		node.fCost = node.gCost + node.hCost;
	}

	public boolean search() {
		while (!this.goalReached && this.step < 500) {
			int col = this.currentNode.col;
			int row = this.currentNode.row;
			this.currentNode.checked = true;
			this.openList.remove(this.currentNode);
			if (row - 1 >= 0)
				openNode(this.node[col][row - 1]);
			if (col - 1 >= 0)
				openNode(this.node[col - 1][row]);
			if (row + 1 < this.gp.maxWorldRow)
				openNode(this.node[col][row + 1]);
			if (col + 1 < this.gp.maxWorldCol)
				openNode(this.node[col + 1][row]);
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			for (int i = 0; i < this.openList.size(); i++) {
				if (this.openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = this.openList.get(i).fCost;
				} else if (this.openList.get(i).fCost == bestNodefCost
						&& this.openList.get(i).gCost < this.openList.get(bestNodeIndex).gCost) {
					bestNodeIndex = i;
				}
			}
			if (this.openList.size() == 0)
				break;
			this.currentNode = this.openList.get(bestNodeIndex);
			if (this.currentNode == this.goalNode) {
				this.goalReached = true;
				trackThePath();
			}
			this.step++;
		}
		return this.goalReached;
	}

	public void openNode(Node node) {
		if (!node.open && !node.checked && !node.solid) {
			node.open = true;
			node.parent = this.currentNode;
			this.openList.add(node);
		}
	}

	public void trackThePath() {
		Node current = this.goalNode;
		while (current != this.startNode) {
			this.pathList.add(0, current);
			current = current.parent;
		}
	}
}
