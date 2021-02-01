package com.systemsouth.avaliacaosystemsouth.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systemsouth.avaliacaosystemsouth.api.dto.MessageDTO;
import com.systemsouth.avaliacaosystemsouth.api.dto.ResponseDTO;
import com.systemsouth.avaliacaosystemsouth.api.dto.ScheduleDTO;
import com.systemsouth.avaliacaosystemsouth.service.ScheduleService;


@RestController
@RequestMapping(value ="/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;


	@GetMapping
	public ResponseEntity<List<ScheduleDTO>> getSchedule() {
		return ResponseEntity.ok(scheduleService.getSchedules());
	}

	@PostMapping
	public ResponseEntity<ScheduleDTO> creatSchedule(@RequestBody ScheduleDTO scheduleDTO) {
		return ResponseEntity.ok(scheduleService.creatSchedule(scheduleDTO));
	}

	@PutMapping
	public ResponseEntity<ScheduleDTO> updateSchedule(@RequestBody ScheduleDTO scheduleDTO) {
		return ResponseEntity.ok(scheduleService.updateSchedule(scheduleDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MessageDTO>  deleteSchedule(@PathVariable Long id) {
		scheduleService.deleteSchedule(id);
		return ResponseEntity.ok(new MessageDTO("Pauta deletada com sucesso"));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<MessageDTO>  startSchedule(@PathVariable Long id) {
		scheduleService.startSchedule(id);
		return ResponseEntity.ok(new MessageDTO("Votação iniciada"));
	}
	 
}
