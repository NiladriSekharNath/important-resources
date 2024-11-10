package com.adidas.lld.cricbuzz.innings;

import com.adidas.lld.cricbuzz.scoreupdater.BattingScoreUpdater;
import com.adidas.lld.cricbuzz.scoreupdater.BowlingScoreUpdater;
import com.adidas.lld.cricbuzz.scoreupdater.ScoreUpdaterObserver;
import com.adidas.lld.cricbuzz.team.Team;
import com.adidas.lld.cricbuzz.team.Wicket;
import com.adidas.lld.cricbuzz.team.WicketType;
import com.adidas.lld.cricbuzz.team.player.PlayerDetails;

import java.util.ArrayList;
import java.util.List;

public class BallDetails {
  public int ballNumber;
  public BallType ballType;
  public RunType runType;
  public PlayerDetails playedBy;
  public PlayerDetails bowledBy;

  public Wicket wicket;
  List<ScoreUpdaterObserver> scoreUpdaterObserverList = new ArrayList<>();

  public BallDetails(int ballNumber) {
    this.ballNumber = ballNumber;
    scoreUpdaterObserverList.add(new BowlingScoreUpdater());
    scoreUpdaterObserverList.add(new BattingScoreUpdater());

  }

  public void startBallDelivery(Team battingTeam, Team bowlingTeam, OverDetails over) {
    playedBy = battingTeam.getStriker();
    this.bowledBy = over.bowledBy;

    //THROW BALL AND GET THE BALL TYPE, assuming here that ball type is always NORMAL
    ballType = BallType.NORMAL;

    if (isWicketTaken()) {
      runType = RunType.ZERO;
      //considering only BOLD
      wicket = new Wicket(WicketType.BOLD, bowlingTeam.getCurrentBowler(), over, this);
      //making only striker out for now
      battingTeam.setStriker(null);
    } else {
      runType = getRunType();

      if (runType == RunType.ONE || runType == RunType.THREE) {
        //swap striket and non striker
        PlayerDetails temp = battingTeam.getStriker();
        battingTeam.setStriker(battingTeam.getNonStriker());
        battingTeam.setNonStriker(temp);
      }
    }

    notifyUpdaters(this);

  }

  private void notifyUpdaters(BallDetails ballDetails) {
    for (ScoreUpdaterObserver observer : scoreUpdaterObserverList) {
      observer.update(ballDetails);
    }
  }

  private boolean isWicketTaken() {
    if (Math.random() < 0.2) {
      return true;
    } else {
      return false;
    }

  }

  private RunType getRunType() {
    double val = Math.random();
    if (val <= 0.2) {
      return RunType.ONE;
    } else if (val >= 0.3 && val <= 0.5) {
      return RunType.TWO;
    } else if (val >= 0.6 && val <= 0.8) {
      return RunType.FOUR;
    } else {
      return RunType.SIX;
    }

  }

}
