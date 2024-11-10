package com.adidas.solidprincipals.liskovsubstitution.betterway.implementation2;

public abstract class EngineVehiclesAssembly extends EngineVehicles{

  private TyreFactory tyreFactory;
  private EngineFactory engineFactory;

  public EngineVehiclesAssembly(TyreFactory tyreFactory, EngineFactory engineFactory){
    this.tyreFactory = tyreFactory ;
    this.engineFactory = engineFactory;
  }

  public void setTyres(){
    super.tyres = this.tyreFactory.makeTyres();
  }

  @Override
  protected void setEngine() {
    super.engine = this.engineFactory.makeEngine();
  }
}
