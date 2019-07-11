package com.growlithe.computer.mysql.computer.video.service;

/**
 * @Author : Growlithe
 * @Date : 2019/2/19 10:09 AM
 * @Description
 */
public class TeamBuyingDetailsDTO {

    private Integer joinMemberCount;

    private Long teamLeaderRecordId;

    public Integer getJoinMemberCount() {
        return joinMemberCount;
    }

    public void setJoinMemberCount(Integer joinMemberCount) {
        this.joinMemberCount = joinMemberCount;
    }

    public Long getTeamLeaderRecordId() {
        return teamLeaderRecordId;
    }

    public void setTeamLeaderRecordId(Long teamLeaderRecordId) {
        this.teamLeaderRecordId = teamLeaderRecordId;
    }

    @Override
    public String toString() {
        return "TeamBuyingDetailsDTO{" +
                "joinMemberCount=" + joinMemberCount +
                ", teamLeaderRecordId=" + teamLeaderRecordId +
                '}';
    }
}
