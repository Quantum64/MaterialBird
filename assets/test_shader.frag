#version 330 core

precision mediump float;
uniform vec4 vColor;

void main() {
    gl_FragColor = vColor;
}
