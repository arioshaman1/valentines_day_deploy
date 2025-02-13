import { createRouter, createWebHistory } from 'vue-router';

// Импортируем компоненты
import Welcome from '@/components/TheWelcome.vue';
import Login from '@/components/LoginView.vue';
import Dashboard from "@/components/Dashboard.vue";
import Registration from "@/components/Registration.vue"

const routes = [
    {
        path: '/',
        name: 'home',
        component: Welcome,
    },
    {
        path: '/login',
        name: 'login',
        component: Login,
    },
    {
        path: '/dashboard',
        name: 'dashboard',
        component: Dashboard,
    },
    {
        path: '/reg',
        name: 'reg',
        component: Registration,
    }
];

const router = createRouter({
    history: createWebHistory(), routes,
});

export default router;