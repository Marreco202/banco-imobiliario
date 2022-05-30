package model;

class SpecialTile extends Tile {
	
	TilesEspeciais tipoDeTileEspecial;

	public SpecialTile(int pos, String nome, TilesEspeciais tipoDeTileEspecial) {
		super(pos, nome);
		this.tipoDeTileEspecial = tipoDeTileEspecial;
	}

}
