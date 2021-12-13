package com.tft.easyfuturedispatch.core.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "event" )
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event {
  private int id;
  @Field("event_name")
  private String eventName;
  @Field("event_description")
  private String eventDescription;
  @Field("event_expected_start_date")
  private Date eventExpectedStartDate;
  @Field("event_expected_end_date")
  private Date eventExpectedEndDate;
  @Field("event_start_date")
  private Date eventStartDate;
  @Field("event_end_date")
  private Date eventEndDate;
  @Field("event_is_free")
  private boolean eventIsFree;
  @Field("event_suscribing_cost")
  private Integer eventSuscribingCost;
  @Field("event_attending_cost")
  private Integer eventAttendingCost;
  @Field("event_participant_target")
  private String eventParticipantTarget;
  @Field("event_duration")
  private String eventDuration;
  @Field("event_program")
  private String eventProgram;

  @DBRef
  private Client category;

  public Event(Object o, String e, Client categoryC1) {
  }
}
