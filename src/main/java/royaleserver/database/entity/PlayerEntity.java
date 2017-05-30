package royaleserver.database.entity;

import org.hibernate.annotations.GenericGenerator;
import royaleserver.database.util.Identifiable;
import royaleserver.logic.Arena;
import royaleserver.logic.ClanRole;
import royaleserver.logic.ExpLevel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "players")
@NamedQueries({
		@NamedQuery(name = ".clear", query = "DELETE FROM PlayerEntity p")
})
public class PlayerEntity implements Identifiable<Long> {
	@Id
	@GenericGenerator(
			name = "assigned-identity",
			strategy = "royaleserver.database.util.AssignedIdentityGenerator"
	)
	@GeneratedValue(
			generator = "assigned-identity",
			strategy = GenerationType.IDENTITY
	)
	private Long id;

	@Column(length = 32, unique = true, nullable = true)
	private String name;

	@Column(nullable = false)
	private int gold;

	@Column(nullable = false)
	private int gems;

	@Column(length = 64, nullable = false)
	private String passToken; // Account security, like password

	@Column(nullable = false)
	@OneToMany(mappedBy = "player")
	private Set<PlayerCardEntity> cards = new HashSet<>();

	@Column(nullable = false)
	private int trophies = 0;

	@JoinColumn(name = "arena_id")
	@ManyToOne(optional = false)
	private ArenaEntity arena;


	@JoinColumn(name = "last_exp_level_id")
	@ManyToOne(optional = false)
	private ExpLevelEntity lastExpLevel;
	
	@JoinColumn(name = "exp_level_id")
	@ManyToOne(optional = false)
	private ExpLevelEntity expLevel;

	@Column(name = "exp_level_experience", nullable = false)
	private int expLevelExperience = 0;

	@Column(nullable = false)
	@OneToMany(mappedBy = "player")
	private Set<HomeChestEntity> homeChests = new HashSet<>();

	@JoinColumn(name = "clan_id")
	@ManyToOne
	private ClanEntity clan = null;

	@JoinColumn(name = "clan_role_id")
	@ManyToOne
	private ClanRoleEntity clanRole = null;


	public Long getId() {
		return id;
	}

	public PlayerEntity setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public PlayerEntity setName(String name) {
		this.name = name;
		return this;
	}

	public int getGold() {
		return gold;
	}

	public PlayerEntity setGold(int gold) {
		this.gold = gold;
		return this;
	}

	public int getGems() {
		return gems;
	}

	public PlayerEntity setGems(int gems) {
		this.gems = gems;
		return this;
	}

	public String getPassToken() {
		return passToken;
	}

	public PlayerEntity setPassToken(String passToken) {
		this.passToken = passToken;
		return this;
	}

	public Set<PlayerCardEntity> getCards() {
		return cards;
	}

	public PlayerEntity setCards(Set<PlayerCardEntity> cards) {
		this.cards = cards;
		return this;
	}

	public int getTrophies() {
		return trophies;
	}

	public PlayerEntity setTrophies(int trophies) {
		this.trophies = trophies;
		return this;
	}

	public ArenaEntity getArena() {
		return arena;
	}

	public PlayerEntity setArena(ArenaEntity arena) {
		this.arena = arena;
		return this;
	}

	public Arena getLogicArena() {
		return Arena.byDB(arena.getId());
	}

	public PlayerEntity setLogicArena(Arena arena) {
		return setArena(new ArenaEntity().setId(arena.getDbId()));
	}

	public ExpLevelEntity getLastExpLevel() {
		return lastExpLevel;
	}

	public PlayerEntity setLastExpLevel(ExpLevelEntity lastExpLevel) {
		this.lastExpLevel = lastExpLevel;
		return this;
	}

	public ExpLevel getLogicLastExpLevel() {
		return ExpLevel.byDB(lastExpLevel.getId());
	}

	public PlayerEntity setLogicLastExpLevel(ExpLevel level) {
		return setLastExpLevel(new ExpLevelEntity().setId(level.getDbId()));
	}
	
	public ExpLevelEntity getExpLevel() {
		return expLevel;
	}

	public PlayerEntity setExpLevel(ExpLevelEntity expLevel) {
		this.expLevel = expLevel;
		return this;
	}
	
	public ExpLevel getLogicExpLevel() {
		return ExpLevel.byDB(expLevel.getId());
	}

	public PlayerEntity setLogicExpLevel(ExpLevel level) {
		return setExpLevel(new ExpLevelEntity().setId(level.getDbId()));
	}

	public int getExpLevelExperience() {
		return expLevelExperience;
	}

	public PlayerEntity setExpLevelExperience(int expLevelExperience) {
		this.expLevelExperience = expLevelExperience;
		return this;
	}

	public Set<HomeChestEntity> getHomeChests() {
		return homeChests;
	}

	public PlayerEntity setHomeChests(Set<HomeChestEntity> homeChests) {
		this.homeChests = homeChests;
		return this;
	}

	public ClanEntity getClan() {
		return clan;
	}

	public PlayerEntity setClan(ClanEntity clan) {
		this.clan = clan;
		return this;
	}

	public ClanRoleEntity getClanRole() {
		return clanRole;
	}

	public PlayerEntity setClanRole(ClanRoleEntity clanRole) {
		this.clanRole = clanRole;
		return this;
	}

	public ClanRole getLogicClanRole() {
		return ClanRole.byDB(clanRole.getId());
	}

	public PlayerEntity setLogicClanRole(ClanRole role) {
		return setClanRole(new ClanRoleEntity().setId(role.getDbId()));
	}
}
