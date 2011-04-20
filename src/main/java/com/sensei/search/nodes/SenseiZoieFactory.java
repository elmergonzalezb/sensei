package com.sensei.search.nodes;

import java.io.File;
import java.util.Comparator;

import proj.zoie.api.Zoie;
import proj.zoie.api.indexing.ZoieIndexableInterpreter;
import proj.zoie.impl.indexing.ZoieConfig;

import com.browseengine.bobo.api.BoboIndexReader;

public abstract class SenseiZoieFactory<D>
{ 
	
  protected final File _idxDir;
  protected final ZoieIndexableInterpreter<D> _interpreter;
  protected final SenseiIndexReaderDecorator _indexReaderDecorator;
  protected final ZoieConfig _zoieConfig;
  
  public SenseiZoieFactory(File idxDir,ZoieIndexableInterpreter<D> interpreter,SenseiIndexReaderDecorator indexReaderDecorator,ZoieConfig zoieConfig){
	  _idxDir = idxDir;
	  _interpreter = interpreter;
	  _indexReaderDecorator = indexReaderDecorator;
	  _zoieConfig = zoieConfig;
  }
  
  public static File getPath(File idxDir,int nodeId,int partitionId){
    File nodeLevelFile = new File(idxDir, "node"+nodeId);  
    return new File(nodeLevelFile, "shard"+partitionId); 
  }
  
  public SenseiIndexReaderDecorator getDecorator(){
	  return _indexReaderDecorator;
  }
  
  public ZoieIndexableInterpreter<D> getInterpreter(){
	  return _interpreter;
  }

  public Comparator<String> getVersionComparator() {
    return _zoieConfig.getVersionComparator();
  }
  
  public abstract Zoie<BoboIndexReader,D> getZoieInstance(int nodeId,int partitionId);

  // TODO: change to getDirectoryManager
  public abstract File getPath(int nodeId,int partitionId);
}
