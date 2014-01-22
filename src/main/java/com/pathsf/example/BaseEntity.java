package com.pathsf.example;


import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.pathsf.example.config.IdGenerator;

@MappedSuperclass
public abstract class BaseEntity {

	/**
	 * uuid is necessary for unsaved objects.
	 */
	@Transient
	private String uuid = IdGenerator.createId();
	
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createDate")
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updateDate")
	private Date updateDate;
	
	@Version
	@Column(name = "version")
	private int version;
	
	@PrePersist
	public void preCreate(){
		this.createDate = GregorianCalendar.getInstance().getTime();
	}
	
	@PreUpdate
	public void preUpdate(){
		this.updateDate = GregorianCalendar.getInstance().getTime();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUuid() {
		return uuid;
	}

	/**
	 * hashcode and equals implementation depends to the uuid (business id) because of the hibernate does not provide id to entity until it saves. 
	 * uuid prevents the object uniqueness while all objects are not in the saved state.
	 */
	@Override
	public int hashCode() {
		
		return ((uuid == null) ? super.hashCode() : uuid.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		BaseEntity other = (BaseEntity) obj;
		
		if (uuid == null){
			return false;
		}
		
		return uuid.equals(other.getUuid());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(" BaseEntity [id=");
		builder.append(id);
		builder.append(", uuid=");
		builder.append(uuid);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}
}