
package pro.ddsr.backendantiques.modules.ranking_antiguedad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.ranking_antiguedad.repository.RankingAntiguedadRepository;
import pro.ddsr.backendantiques.modules.ranking_antiguedad.entity.RankingAntiguedad;

@Service
public class RankingAntiguedadService {
    @Autowired
    RankingAntiguedadRepository ranking_antiguedadRepository;

    @Transactional
    public Optional<RankingAntiguedad> delete(Long id) {
        Optional<RankingAntiguedad> optionalRankingAntiguedad = this.ranking_antiguedadRepository.findById(id);
        optionalRankingAntiguedad.ifPresent(
            RankingAntiguedadFound -> {
                this.ranking_antiguedadRepository.delete(RankingAntiguedadFound);
            }
        );
        return optionalRankingAntiguedad;
    }
 
    public List<RankingAntiguedad> findAll() {
        return (List<RankingAntiguedad>) this.ranking_antiguedadRepository.findAll();
    }

    public Optional<RankingAntiguedad> findById(Long id) {
        return this.ranking_antiguedadRepository.findById(id);
    }

    public RankingAntiguedad save(RankingAntiguedad RankingAntiguedad) {
        return this.ranking_antiguedadRepository.save(RankingAntiguedad);
    }

    public Optional<RankingAntiguedad> update(Long id, RankingAntiguedad ranking_antiguedad) {
        Optional<RankingAntiguedad> optionalRankingAntiguedad = this.ranking_antiguedadRepository.findById(id);
        if (optionalRankingAntiguedad.isPresent()) {
            RankingAntiguedad ranking_antiguedadItem = optionalRankingAntiguedad.orElseThrow();
            // Sets
            return Optional.of(this.ranking_antiguedadRepository.save(ranking_antiguedadItem));
        }
        return optionalRankingAntiguedad;
    }
}
