package model;

class SpecialTile extends Tile {
	
	private TilesEspeciais tipoDeTileEspecial;

	public SpecialTile(int pos, String nome, TilesEspeciais tipoDeTileEspecial) {
		super(pos, nome);
		this.tipoDeTileEspecial = tipoDeTileEspecial;
	}

	public TilesEspeciais getTipoTileEspecial() {
		return this.tipoDeTileEspecial;
	}
	
}
