package org.firstinspires.ftc.teamcode;

public enum RobotState {

    BASE(445,0,1, "Base"),
    SPNM_GRAB(445,459,0.95, "SPNM_GRAB"),
    SPMN_COLLECT(445,1160,1, "SPMN_COLLECT"),
    HIGH_SPMN_SETUP(445,2800,1,"HIGH_SPMN_SETUP"),
    LOW_SPMN_SETUP(445,1100,1,"LOW_SPMN_SETUP"),
    LOW_SPMN_SCORE(445,146,1,"SPMN_SCORE"),
    HIGH_SPMN_SCORE(445,1854,1,"SPMN_SCORE"),
    SMPL_LOAD(10,146,1, "SMPL_LOAD"),
    PRE_PICKUP_SMPL(5400,0,1,"PRE_PICKUP_SMPL"),
    PICKUP_SMPL(5500,0,1,"PICKUP_SMPL"),
    SMPL_SETUP(445,2924,1,"SMPL_SETUP"),
    SMPL_SCORE(445,2924,0.5,"SMPL_SCORE");

    private int armPosition;
    private int slidePosition;
    private double bucketPosition;
    private String name;

    //          Ticks,      cm from Base,  0-1
    RobotState(int arm, int slide, double bucket, String name){
        armPosition = arm;
        slidePosition = slide;
        bucketPosition = bucket;
        this.name = name;
    }

    public int getArmPosition(){
        return armPosition;
    }

    public int getSlidePosition() {
        return slidePosition;
    }

    public double getBucketPosition() {
        return bucketPosition;
    }

    public String getName() {
        return name;
    }

    public void setArmPosition(int armPosition) {
        this.armPosition = armPosition;
    }

    public void setSlidePosition(int slidePosition) {
        this.slidePosition = slidePosition;
    }

    public void setBucketPosition(double bucketPosition) {
        this.bucketPosition = bucketPosition;
    }

}
