package com.systemsouth.avaliacaosystemsouth.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsouth.avaliacaosystemsouth.api.dto.ScheduleDTO;
import com.systemsouth.avaliacaosystemsouth.domain.Schedule;
import com.systemsouth.avaliacaosystemsouth.repository.ScheduleRepository;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository repository;

	public List<ScheduleDTO> getSchedules() {
		List<Schedule> list = repository.findAll();

		List<ScheduleDTO> scheduleDTOs = new ArrayList<ScheduleDTO>();
		list.stream().forEach(schedule -> {
			scheduleDTOs.add(new ScheduleDTO(schedule));
		});

		return scheduleDTOs;
	}

	public ScheduleDTO creatSchedule(ScheduleDTO scheduleDTO) {

		Schedule schedule = new Schedule();
		schedule.setText(scheduleDTO.getText());

		schedule = repository.save(schedule);
		return new ScheduleDTO(schedule);
	}

	public ScheduleDTO updateSchedule(ScheduleDTO scheduleDTO) {
		// validar se o campo vim vazio
		Schedule schedule = new Schedule();
		schedule.setId(scheduleDTO.getId());
		schedule.setText(scheduleDTO.getText());
		schedule.setVotingStart(scheduleDTO.getVotingStart());

		schedule = repository.save(schedule);
		return new ScheduleDTO(schedule);
	}

	public void deleteSchedule(Long id) {
		Schedule schedule = new Schedule();
		schedule.setId(id);

		repository.delete(schedule);
	}

	public void startSchedule(Long id) {
		//editar opition para soltar erro se tiver vazio
		Schedule schedule = repository.findById(id).get();
		schedule.setVotingStart(LocalDateTime.now());
	
		repository.save(schedule);
	}

	public Schedule findById(Long id) {
		return repository.findById(id).get();
	}

}
