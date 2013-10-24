package com.callistech.policyserver.dsm.session.managers;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.callistech.policyserver.af.exception.AttributeNotFoundException;
import com.callistech.policyserver.dsm.common.DynamicServiceLimits;
import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.common.DynamicSessionKey;
import com.callistech.policyserver.dsm.common.Limits;
import com.callistech.policyserver.dsm.common.MatchCriteria;
import com.callistech.policyserver.dsm.common.subscriber.SubscriberDS;
import com.callistech.policyserver.dsm.session.SessionManager;
import com.callistech.policyserver.psm.entities.vo.gui.systemsettings.dynamicsessions.formatter.DynamicSubscriberIdFormatterVO;
import com.callistech.policyserver.psm.entities.vo.gui.systemsettings.dynamicsessions.mappings.DynamicServiceMappingItemVO;
import com.callistech.policyserver.psm.entities.vo.gui.systemsettings.dynamicsessions.mappings.DynamicServiceMappingVO;

public class ConfigurationManager {

	private SessionManager sessionManager;
	private Logger logger = Logger.getLogger(getClass());

	public ConfigurationManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public String formatSubscriberId(String subscriberId) {
		// Afanado del AF
		DynamicSubscriberIdFormatterVO subscriberIdFormatter = null;
		try {
			if (sessionManager.getSessionModule().getConfigurationAFManager() != null && sessionManager.getSessionModule().getConfigurationAFManager().getDynamicSessionsConfiguration() != null) {
				subscriberIdFormatter = sessionManager.getSessionModule().getConfigurationAFManager().getDynamicSessionsConfiguration().getDynamicSubscriberIdFormatter();
				if (subscriberIdFormatter != null) {
					return formaterSubscriberId(subscriberId, subscriberIdFormatter.getEnabled(), subscriberIdFormatter.getTechnoSubscriberEnabled(), subscriberIdFormatter.getRegularExpressions());
				}
			} else {
				// Exception, la configuracion no esta bien seteada
			}
		} catch (Exception ex) {
			// Exception, error al formatear
			ex.printStackTrace();
		}
		return null;
	}

	private String formaterSubscriberId(String subscriberId, Boolean enableFormat, Boolean enableFormatAttribute, List<String> regularExpresions) {
		// Afanado del AF
		String subscriberIdOut = subscriberId;
		Pattern pattern = null;
		Matcher matcher = null;
		int groupCount = 0;
		Boolean encontrado = Boolean.FALSE;

		// Verifica que este habilitado el formateo
		if (enableFormat) {
			if (enableFormatAttribute) {
				subscriberIdOut = "";
				// Verifica que exista alguna regex
				if (regularExpresions != null) {
					// Recorre las regex existentes hasta matchear con una
					for (String regex : regularExpresions) {
						if (regex != null) {
							pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL | Pattern.UNICODE_CASE | Pattern.UNIX_LINES);
							matcher = pattern.matcher(subscriberId);
							// Si matchea la regex arma el resultado
							if (matcher.find()) {
								groupCount = matcher.groupCount();
								for (int i = 1; i <= groupCount; i++) {
									subscriberIdOut += matcher.group(i);
								}
								encontrado = Boolean.TRUE;
							}
							// Al primer matcheo, corta
							if (encontrado) {
								break;
							}
						}
					}
				}
			}
		}

		return subscriberIdOut;
	}

	public DynamicServiceLimits getDynamicServiceLimits(SubscriberDS subscriberDS, String dsd) throws AttributeNotFoundException {
		// Robado del AF, agarro el item y de ahi genero nuestro objeto
		DynamicServiceMappingItemVO value = null;
		Collection<DynamicServiceMappingItemVO> mappings = null;
		String regex = "";
		String data = "";
		Pattern pattern = null;
		Matcher matcher = null;

		DynamicServiceMappingVO dynamicServiceMappingVO = sessionManager.getSessionModule().getConfigurationAFManager().getDynamicSessionsConfiguration().getDynamicServiceMapping();
		if (dynamicServiceMappingVO.getEnabled()) {
			logger.debug("mapping dynamic service to properties is enabled");
			if (dsd == null || (dsd != null && dsd.isEmpty())) {
				if (dynamicServiceMappingVO.getActivateWithoutValue()) {
					value = getDynamicServiceMappingItemVODefault(dynamicServiceMappingVO);
				} else {
					throw new AttributeNotFoundException("Dynamic service descriptor is mandatory");
				}
			} else {
				logger.debug("dynamic service descriptor is " + dsd);
				data = dsd;
				mappings = dynamicServiceMappingVO.getDynamicServiceMappingItemValues();
				if (mappings != null) {
					for (DynamicServiceMappingItemVO item : mappings) {
						if (item != null) {
							regex = item.getRegex();
							if (item.getSiteId() == -1 || item.getSiteId().equals(subscriberDS.getSiteId())) {
								if (item.getTierId() == -1 || item.getTierId().equals(subscriberDS.getTierId())) {
									if (regex != null) {
										pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL | Pattern.UNICODE_CASE | Pattern.UNIX_LINES);
										matcher = pattern.matcher(data);
										logger.debug("search mapping item in data " + data + " with regex " + regex + " site id " + subscriberDS.getSiteId() + " tier id " + subscriberDS.getTierId());
										if (matcher.find()) {
											value = item;
											logger.info("set property " + value + " from regex " + regex + " and data " + data);
											break;
										} else {
											logger.debug("search mapping item in data " + data + " with regex " + regex + " not match");
										}
									} else {
										logger.error("search mapping item in data " + data + " regex is null");
									}
								} else {
									logger.debug("the tier id " + item.getTierId() + " of item not equals for location tier id " + subscriberDS.getTierId());
								}
							} else {
								logger.debug("the site id " + item.getSiteId() + " of item not equals for location site id " + subscriberDS.getSiteId());
							}
						} else {
							logger.info("tier mapping item is null");
						}
					}
				} else {
					logger.info("tier mapping is null");
				}
				if (value == null) {
					if (dynamicServiceMappingVO.getActivateWithoutValue()) {
						logger.info("dynamic service descriptor " + dsd + " not found in configuration mapping active without value is enabled");
						value = getDynamicServiceMappingItemVODefault(dynamicServiceMappingVO);
					} else {
						throw new AttributeNotFoundException("Dynamic service descriptor " + dsd + " not found in configuration mapping for subscriber id " + subscriberDS.getSubscriberId());
					}

				}
			}
		} else {
			throw new AttributeNotFoundException("Dynamic service mapping configuration for " + dsd + " is disabled for subscriber id " + subscriberDS.getSubscriberId());
		}
		return generateDynamicService(dsd, value);
	}

	private DynamicServiceMappingItemVO getDynamicServiceMappingItemVODefault(DynamicServiceMappingVO dynamicServiceMappingVO) {
		return dynamicServiceMappingVO.getDynamicServiceMappingItem(dynamicServiceMappingVO.getDefaultValue());
	}

	private DynamicServiceLimits generateDynamicService(String dsd, DynamicServiceMappingItemVO value) {
		DynamicServiceLimits dynamicService = new DynamicServiceLimits();
		dynamicService.setDynamicService(sessionManager.getSessionModule().getConfigurationAFManager().getBodServiceConfiguration().getBodService(value.getDynamicServiceId()));
		dynamicService.setLimits(generateLimits(value));
		return dynamicService;
	}

	private Limits generateLimits(DynamicServiceMappingItemVO value) {
		Limits limits = new Limits();
		limits.setCountingType(value.getCountingType());
		limits.setDuration(value.getDuration());
		limits.setVolumeBoth(value.getVolumeBoth());
		limits.setVolumeDownstream(value.getVolumeDownstream());
		limits.setVolumeUpstream(value.getVolumeUpstream());
		return limits;
	}

	public DynamicSession generateDynamicSession(SubscriberDS subscriberDS, DynamicServiceLimits dynamicServiceLimits, Integer sessionId, Long startTime) {
		DynamicSession dynamicSession = new DynamicSession();
		dynamicSession.setSubscriberId(subscriberDS.getSubscriberId());
		dynamicSession.setSessionId(sessionId);
		dynamicSession.setServiceId(dynamicServiceLimits.getDynamicService().getId());
		dynamicSession.setStartTime(startTime);
		dynamicSession.setLimits(dynamicServiceLimits.getLimits());
		if (dynamicServiceLimits.getDynamicService().getTimeframe(0).getRule(0).getSessionDeactivation().isMatchAll()) {
			dynamicSession.setMatchCriteria(MatchCriteria.MATCH_ALL);
		}
		if (dynamicServiceLimits.getDynamicService().getTimeframe(0).getRule(0).getSessionDeactivation().isMatchAny()) {
			dynamicSession.setMatchCriteria(MatchCriteria.MATCH_ANY);
		}
		dynamicSession.setTimeLimitEnabled(dynamicServiceLimits.getDynamicService().getTimeframe(0).getRule(0).getSessionDeactivation().isQuotaTimeDepleted());
		dynamicSession.setDownVolumeLimitEnabled(dynamicServiceLimits.getDynamicService().getTimeframe(0).getRule(0).getSessionDeactivation().isQuotaVolumeDownstreamDepleted());
		dynamicSession.setUpVolumeLimitEnabled(dynamicServiceLimits.getDynamicService().getTimeframe(0).getRule(0).getSessionDeactivation().isQuotaVolumeUpstreamDepleted());
		return dynamicSession;
	}

	public DynamicSessionKey generateDynamicSessionKey(SubscriberDS subscriberDS, DynamicServiceLimits dynamicServiceLimits) {
		DynamicSessionKey key = new DynamicSessionKey();
		key.setSubscriberId(subscriberDS.getSubscriberId());
		key.setDsId(dynamicServiceLimits.getDynamicService().getId());
		return key;
	}
}
