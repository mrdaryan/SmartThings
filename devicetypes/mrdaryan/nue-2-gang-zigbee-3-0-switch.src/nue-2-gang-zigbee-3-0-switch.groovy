/**
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Based on on original by Lazcad / RaveTam /Netsheriff
 *  Mods for Nue ZigBee 3.0 2 Gang Switch by Kevin X. 3A Smart Home
 *  Mods for Nue ZigBee 3.0 2 Gang Switch by mrdaryan.
 */

metadata {
	definition (name: "Nue 2 Gang ZigBee 3.0 Switch", namespace: "mrdaryan", author: "mrdaryan") {
        capability "Actuator"
        capability "Configuration"
        capability "Refresh"
        capability "Switch"
        capability "Health Check"
  
        fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006"
        fingerprint profileId: "0104", inClusters: "0000, 0003, 0006", outClusters: "0003, 0006, 0019, 0406", manufacturer: "Leviton", model: "ZSS-10", deviceJoinName: "Leviton Switch"
        fingerprint profileId: "0104", inClusters: "0000, 0003, 0006", outClusters: "000A", manufacturer: "HAI", model: "65A21-1", deviceJoinName: "Leviton Wireless Load Control Module-30amp"
        fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006", outClusters: "0003, 0006, 0008, 0019, 0406", manufacturer: "Leviton", model: "DL15A", deviceJoinName: "Leviton Lumina RF Plug-In Appliance Module"
        fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006", outClusters: "0003, 0006, 0008, 0019, 0406", manufacturer: "Leviton", model: "DL15S", deviceJoinName: "Leviton Lumina RF Switch"
        fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006", outClusters: "0003, 0006, 0008, 0019, 0406", manufacturer: "FeiBit", model: "FNB56-ZSW03LX2.0", deviceJoinName: "Nue 2 Gang ZigBee 3.0 Switch"
        
        attribute "lastCheckin", "string"
        attribute "switch", "string"
        attribute "switch1", "string"
    	attribute "switch2", "string"

    	command "on1"
    	command "off1"
	    command "on2"
		command "off2"
        
        attribute "switch1","ENUM",["on","off"]
        attribute "switch2","ENUM",["on","off"]
}

	preferences {
		input(name: "DefaultSwitch", type: "enum", title: "Switch to use for default device on/off", options: ["SW1","SW2","Both"], defaultValue: "SW1", required: true, displayDuringSetup: true)
}

    // simulator metadata
    simulator {
        // status messages
        status "on": "on/off: 1"
        status "off": "on/off: 0"
        
        status "switch1 on": "on/off: 1"
		status "switch1 off": "on/off: 0"
        status "switch2 on": "on/off: 1"
		status "switch2 off": "on/off: 0"

        // reply messages
        reply "zcl on-off on": "on/off: 1"
        reply "zcl on-off off": "on/off: 0"
}
    tiles(scale: 1) {
     multiAttributeTile(name:"switch", type: "device.switch", width: 1, height: 1, canChangeIcon: true){
            tileAttribute ("device.switch", key: "PRIMARY_CONTROL") { 
                attributeState "on", label:'${name}', action:"off", icon:"st.switches.light.on", backgroundColor:"#00a0dc", nextState:"turningOff"
                attributeState "off", label:'${name}', action:"on", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
                attributeState "turningOn", label:'${name}', action:"", icon:"st.switches.light.on", backgroundColor:"#00a0dc", nextState:"turningOff"
                attributeState "turningOff", label:'${name}', action:"", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
            }
           	tileAttribute("device.lastCheckin", key: "SECONDARY_CONTROL") {
    			attributeState("default", label:'Last Update: ${currentValue}',icon: "st.Health & Wellness.health9")
		   	}
        }
        multiAttributeTile(name:"switch1", type: "device.switch", width: 1, height: 1, canChangeIcon: true){
            tileAttribute ("device.switch1", key: "PRIMARY_CONTROL") { 
                attributeState "on", label:'SW1 On', action:"off1", icon:"st.switches.light.on", backgroundColor:"#00a0dc", nextState:"turningOff"
                attributeState "off", label:'SW1 Off', action:"on1", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
                attributeState "turningOn", label:'Turning On', action:"", icon:"st.switches.light.on", backgroundColor:"#00a0dc", nextState:"turningOff"
               attributeState "turningOff", label:'Turning Off', action:"", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
            }
           	tileAttribute("device.lastCheckin", key: "SECONDARY_CONTROL") {
    			attributeState("default", label:'Last Update: ${currentValue}',icon: "st.Health & Wellness.health9")
		   	}
        }
        multiAttributeTile(name:"switch2", type: "device.switch", width: 1, height: 1, canChangeIcon: true){
            tileAttribute ("device.switch2", key: "PRIMARY_CONTROL") { 
                attributeState "on", label:'SW2 On', action:"off2", icon:"st.switches.light.on", backgroundColor:"#00a0dc", nextState:"turningOff"
                attributeState "off", label:'SW2 Off', action:"on2", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
                attributeState "turningOn", label:'Turning On', action:"", icon:"st.switches.light.on", backgroundColor:"#00a0dc", nextState:"turningOff"
                attributeState "turningOff", label:'Turning Off', action:"", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
            }
           	tileAttribute("device.lastCheckin", key: "SECONDARY_CONTROL") {
    			attributeState("default", label:'Last Update: ${currentValue}',icon: "st.Health & Wellness.health9")
		   	}
        }
        
		standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat", width: 1, height: 1) {
            state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
            }
		main(["switch"])
			details(["switch1","switch2", "refresh"])
	}
}

// Parse incoming device messages to generate events

def parse(String description) {
	log.debug "Parsing '${description}'"

	def value = zigbee.parse(description)?.text
	//log.debug "Parse: $value"
	Map map = [:]
	
	if (description?.startsWith('catchall:')) {
		map = parseCatchAllMessage(description)
	}
//    else if (description?.startsWith('on/off: ')){
//    log.debug "onoff"
//    def refreshCmds = zigbee.readAttribute(0x0006, 0x0000, [destEndpoint: 0x0C]) +
//    				  zigbee.readAttribute(0x0006, 0x0000, [destEndpoint: 0x0B])
//    
//	return refreshCmds.collect { new physicalgraph.device.HubAction(it) }     
//    	def resultMap = zigbee.getKnownDescription(description)
//   		log.debug "${resultMap}"
//        
//        map = parseCustomMessage(description) 
//    }

	//log.debug "Parse returned $map"
    //  send event for heartbeat    
    def now = new Date()
   
    sendEvent(name: "lastCheckin", value: now)
    
	def results = map ? createEvent(map) : null
	return results;
}

private Map parseCatchAllMessage(String description) {
    Map resultMap = [:]
	def cluster = zigbee.parse(description)
    def onoff = null

    if (cluster.command == 0x01 || cluster.command == 0x0A)
    	{
        onoff = cluster.data[-1]
        }
	else if (cluster.command == 0x0B)
    	{
        onoff = cluster.data[-2]
        }
    
	log.debug "Command: ${cluster.command} ClusterID: ${cluster.clusterId} Endpoint: ${cluster.sourceEndpoint} On/Off: ${onoff} DefaultSwitch ${DefaultSwitch}"

	if (cluster.clusterId == 0x0006 && cluster.sourceEndpoint == 0x0B){
        if (onoff == 1)
        	{
        	log.debug "SW1 ON"
			def OtherSwitchState = device.currentState("switch2").getValue()
    		if (DefaultSwitch.toUpperCase() == "BOTH" && OtherSwitchState == "on") {
            	sendEvent(name: "switch", value: "on")
            	}
			resultMap = createEvent(name: "switch1", value: "on")
            }
        else if (onoff == 0)
        	{
	    	log.debug "SW1 OFF"
			if (DefaultSwitch.toUpperCase() == "BOTH") {
		    	sendEvent(name: "switch", value: "off")
    			}
			resultMap = createEvent(name: "switch1", value: "off")
        	}
		}

	if (cluster.clusterId == 0x0006 && cluster.sourceEndpoint == 0x0C){
        if (onoff == 1)
        	{
    		log.debug "SW2 ON"
			def OtherSwitchState = device.currentState("switch1").getValue()
    		if (DefaultSwitch.toUpperCase() == "BOTH" && OtherSwitchState == "on") {
            	sendEvent(name: "switch", value: "on")
            	}
            resultMap = createEvent(name: "switch2", value: "on")
			}

		else if (onoff == 0)
        	{
    		log.debug "SW2 OFF"
			if (DefaultSwitch.toUpperCase() == "BOTH") {
		    	sendEvent(name: "switch", value: "off")
    			}
            resultMap = createEvent(name: "switch2", value: "off")
			}
		}
	return resultMap
}

//default on action
def on() {
	log.debug "on() executing"
    sendEvent(name: "switch", value: "on")

//We got the "on" request now which switch to use.
	switch(DefaultSwitch.toUpperCase()) {
	case "BOTH":
    	log.debug "Both switches coming on"
    	[
		"st cmd 0x${device.deviceNetworkId} 0x0B 0x0006 0x1 {}", "delay 500",
    	"st cmd 0x${device.deviceNetworkId} 0x0C 0x0006 0x1 {}", "delay 500"
		]
    	break
	case "SW1":
        log.debug "SW1 coming on"
		"st cmd 0x${device.deviceNetworkId} 0x0B 0x0006 0x1 {}"
		break
	case "SW2":
		log.debug "SW2 coming on"
		"st cmd 0x${device.deviceNetworkId} 0x0C 0x0006 0x1 {}"
		break
	default:
		log.debug "No default switch"
	    break
	}
}

//default off action
def off() {
    log.debug "off() executing"
    sendEvent(name: "switch", value: "off")
    
    //We got the "off" request now which switch to use.
	switch(DefaultSwitch.toUpperCase()) {
	case "BOTH":
    	log.debug "Both switches going off"
    	[
		"st cmd 0x${device.deviceNetworkId} 0x0B 0x0006 0x0 {}", "delay 500",
    	"st cmd 0x${device.deviceNetworkId} 0x0C 0x0006 0x0 {}", "delay 500"
		]
    	break
	case "SW1":
        log.debug "SW1 going off"
		"st cmd 0x${device.deviceNetworkId} 0x0B 0x0006 0x0 {}"
		break
	case "SW2":
		log.debug "SW2 going off"
		"st cmd 0x${device.deviceNetworkId} 0x0C 0x0006 0x0 {}"
		break
	default:
		log.debug "No default switch"
	    break
	}
}

def on1() {
	log.debug "on1() executing"
    def OtherSwitchState = device.currentState("switch2").getValue()
    if (DefaultSwitch.toUpperCase() == "BOTH" && OtherSwitchState == "on") {
    	on()
    }
	else if (DefaultSwitch.toUpperCase() == "SW1") {
    	on()
	}
    else {
    "st cmd 0x${device.deviceNetworkId} 0x0B 0x0006 0x1 {}"
    }
}

def off1() {
    log.debug "off1() executing"
    if (DefaultSwitch.toUpperCase() == "BOTH") {
    	sendEvent(name: "switch", value: "off")
    }
	if (DefaultSwitch.toUpperCase() == "SW1") {
        off()
	}
    else {
   	"st cmd 0x${device.deviceNetworkId} 0x0B 0x0006 0x0 {}"
    }
}

def on2() {
	log.debug "on2() executing"
	def OtherSwitchState = device.currentState("switch1").getValue()
	if (DefaultSwitch.toUpperCase() == "BOTH" && OtherSwitchState == "on") {
    	on()
    }
	else if (DefaultSwitch.toUpperCase() == "SW2") {
    	on()
	}
    else {
    "st cmd 0x${device.deviceNetworkId} 0x0C 0x0006 0x1 {}"
    }
}

def off2() {
    log.debug "off2() executing"
	if (DefaultSwitch.toUpperCase() == "BOTH") {
    	sendEvent(name: "switch", value: "off")
    }
	if (DefaultSwitch.toUpperCase() == "SW2") {
    	off()
	}
	else {
    "st cmd 0x${device.deviceNetworkId} 0x0C 0x0006 0x0 {}"
    }
}

def ping() {
	return refresh()
}

def refresh() {
	log.debug "refreshing"
    [
    "st rattr 0x${device.deviceNetworkId} 0x0B 0x0006 0x0", "delay 500",
	"st rattr 0x${device.deviceNetworkId} 0x0C 0x0006 0x0", "delay 500"
    ]
}

//private Map parseCustomMessage(String description) {
//	def result
//	if (description?.startsWith('on/off: ')) {
//   	if (description == 'on/off: 0')
//    		result = createEvent(name: "switch", value: "off")
//    	else if (description == 'on/off: 1')
//    		result = createEvent(name: "switch", value: "on")
//	}
//    return result
//}