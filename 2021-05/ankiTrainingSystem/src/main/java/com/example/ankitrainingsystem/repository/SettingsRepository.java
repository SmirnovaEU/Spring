package com.example.ankitrainingsystem.repository;

import com.example.ankitrainingsystem.model.Settings;
import com.example.ankitrainingsystem.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends CrudRepository<Settings, Long> {
    Settings findFirstByUser(User user);
}
