package com.example._2ch.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name="posts")
public class Post {
	/*
	make post functionality for bulletin board
	 */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String content;
	/*
	I will implement them later.
	 */
//	private String author;
//	private String date;
//	private String time;




}
