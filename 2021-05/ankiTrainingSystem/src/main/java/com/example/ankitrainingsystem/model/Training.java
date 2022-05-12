package com.example.ankitrainingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainings")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Dictionary.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "dict_id", nullable = false)
    private Dictionary dictionary;

    @Column(name = "repeat", nullable = false)
    private boolean isRepeat;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "word_id", nullable = false)
    private List<Word> words;

    public Training(Dictionary dictionary, boolean isRepeat, List<Word> words) {
        this.dictionary = dictionary;
        this.isRepeat = isRepeat;
        this.words = words;
    }
}
