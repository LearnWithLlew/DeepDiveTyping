package org.teachingkidsprogramming.typingdeepdive;

public class Score
{
  private int sharks;
  private int batch;
  public void scoreKill(Shark shark)
  {
    setSharks(getSharks() + 1);
  }
  public int getSharks()
  {
    return sharks;
  }
  public void setSharks(int sharks)
  {
    this.sharks = sharks;
  }
  public void scoreNewBatch()
  {
    setBatch(getBatch() + 1);
  }
  public int getBatch()
  {
    return batch;
  }
  public void setBatch(int batch)
  {
    this.batch = batch;
  }
}
