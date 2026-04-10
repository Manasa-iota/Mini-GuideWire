package com.insureflow.claims.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "claims")
public class Claim {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true) private String claimNumber;
    @Column(nullable = false) private Long policyId;
    @Column(nullable = false) private Long customerId;
    private String claimType;
    private LocalDate incidentDate;
    private BigDecimal claimAmount;
    private BigDecimal approvedAmount;
    @Column(length = 2000) private String description;
    @Enumerated(EnumType.STRING) private ClaimStatus status;
    private String assignedAdjuster;

    public Long getId(){return id;} public String getClaimNumber(){return claimNumber;} public void setClaimNumber(String v){claimNumber=v;}
    public Long getPolicyId(){return policyId;} public void setPolicyId(Long v){policyId=v;}
    public Long getCustomerId(){return customerId;} public void setCustomerId(Long v){customerId=v;}
    public String getClaimType(){return claimType;} public void setClaimType(String v){claimType=v;}
    public LocalDate getIncidentDate(){return incidentDate;} public void setIncidentDate(LocalDate v){incidentDate=v;}
    public BigDecimal getClaimAmount(){return claimAmount;} public void setClaimAmount(BigDecimal v){claimAmount=v;}
    public BigDecimal getApprovedAmount(){return approvedAmount;} public void setApprovedAmount(BigDecimal v){approvedAmount=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public ClaimStatus getStatus(){return status;} public void setStatus(ClaimStatus v){status=v;}
    public String getAssignedAdjuster(){return assignedAdjuster;} public void setAssignedAdjuster(String v){assignedAdjuster=v;}
}
