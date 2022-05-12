package com.example.ankitrainingsystem.repository;

import com.example.ankitrainingsystem.model.Settings;
import com.example.ankitrainingsystem.model.User;
import org.springframework.data.repository.CrudRepository;

public interface SettingsRepository extends CrudRepository<Settings, Long> {
    Settings findFirstByUSer(User user);
}
